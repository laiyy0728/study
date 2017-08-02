package ftp_jar;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

/**
 * Created by laiyy
 * Date 2017/8/1.
 */
public class FtpUtils implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(FtpUtils.class);

    private FTPClient ftpClient = null;

    private String server;

    private String username;

    private String password;

    private static final int PORT = 21;

    private String localUrl;

    private String remoteUrl;

    private InputStream inputStream;

    private String fileName;

    private String html;

    private int flag;

    public FtpUtils(String server, String username, String password, String localUrl, String remoteUrl, InputStream inputStream, String fileName, String html, int flag) {
        this.server = server;
        this.username = username;
        this.password = password;
        this.localUrl = localUrl;
        this.remoteUrl = remoteUrl;
        this.flag = flag;
        this.inputStream = inputStream;
        this.fileName = fileName;
        this.html = html;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(server); // 链接 FTP
            ftpClient.login(username, password); // 登录 FTP
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); // 文件类型，默认是 ASCII
            ftpClient.enterLocalPassiveMode(); // 设置被动模式
            ftpClient.setConnectTimeout(60000); // 链接超时时间 1 分钟
            ftpClient.setDataTimeout(60000); // 传输超时时间 1 分钟
            ftpClient.setControlEncoding("UTF-8");
            int code = ftpClient.getReplyCode();
            logger.info(" FTP 连接代码：" + code);
            if (!FTPReply.isPositiveCompletion(code)) {
                closeFTPClient();
                ftpClient = null;
                logger.info(" FTP 链接出错");
                throw new RuntimeException("登录 FTP 出错：" + server + "，" + username + "，" + password);
            }
            logger.info(" FTP 连接成功");
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除文件
     */
    public void delete() {
        boolean flag = false;
        try {
            ftpClient.changeWorkingDirectory(remoteUrl);
            flag = ftpClient.deleteFile(fileName);
            if (!flag) {
                logger.info("删除失败！" + remoteUrl + "/" + fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     */
    public void uploadFile() {
        logger.info(" FTP 上传二进制文件");
        boolean flag = false;
        try {
            changeDirectory(remoteUrl);
            logger.info(" FTP 开始上传二进制文件");
            flag = ftpClient.storeFile(fileName, inputStream);
            if (!flag) {
                logger.info("上传失败！" + remoteUrl);
            }
            inputStream.close();
        } catch (Exception e) {
            logger.info("上传出错！" + e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 文本 FTP 上传
     */
    public void uploadHtml() {
        logger.info(" FTP 上传文本");
        boolean result = false;
        if (StringUtils.isNotBlank(html)) {
            InputStream inputStream = null;
            try {
                inputStream = new ByteArrayInputStream(html.getBytes("UTF-8"));
                changeDirectory(remoteUrl);
                logger.info(" FTP 开始上传文件");
                result = ftpClient.storeFile(fileName, inputStream);
                if (!result) {
                    logger.info("上传出错！ 远程地址：" + remoteUrl);
                }
                inputStream.close();
            } catch (Exception e) {
                logger.info("上传出错：" + e.getMessage());
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 更换 FTP 目录
     *
     * @param remoteFoldPath
     */
    public void changeDirectory(String remoteFoldPath) {
        try {
            if (remoteFoldPath.length() > 1) {
                String[] sts = remoteFoldPath.split("/");
                for (String s : sts) {
                    boolean flag = ftpClient.changeWorkingDirectory(s);
                    if (!flag) {
                        ftpClient.makeDirectory(s);
                        ftpClient.changeWorkingDirectory(s);
                    }
                }

            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

    }

    /**
     * 关闭 FTP 链接
     */
    public void closeFTPClient() {
        closeFTPClient(this.ftpClient);
    }

    /**
     * 关闭 FTP 链接
     *
     * @param ftpClient
     */
    public void closeFTPClient(FTPClient ftpClient) {
        try {
            logger.info("正在关闭 FTP 连接");
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            logger.info("关闭 FTP 链接失败！");
        }
    }

    /**
     * 退出 FTP
     */
    public void logout() {
        try {
            logger.info("正在退出 FTP ");
            this.ftpClient.logout();
        } catch (Exception e) {
            e.printStackTrace();
            if (this.ftpClient.isConnected()) {
                try {
                    this.ftpClient.disconnect();
                } catch (IOException f) {
                    logger.info("关闭连接失败");
                }
            }
        } finally {
            if (this.ftpClient.isConnected()) {
                try {
                    this.ftpClient.disconnect();
                } catch (IOException f) {
                    logger.info("关闭连接失败");
                }
            }
        }
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static int getPORT() {
        return PORT;
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    @Override
    public void run() {
        try {
            switch (flag) {
                case 1:
                    // 上传文本文件
                    this.uploadHtml();
                    this.logout();
                    break;
                case 2:
                    // 删除文件
                    this.delete();
                    this.logout();
                    break;
                case 3:
                    // 上传二进制文件
                    this.uploadFile();
                    this.logout();
                    break;
                default:
                    this.logout();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
