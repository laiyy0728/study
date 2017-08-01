package ftp;

import ftp.util.FetchData;
import ftp.util.ResourceUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;


public class FtpUtils implements Runnable {


    private static final Logger log = LoggerFactory.getLogger(FetchData.class);

    private FTPClient ftp = null;

    private String server;
    private String username;
    private String password;
    private int port = 21;
    private String localFile;
    private String remoteFile;
    private int flag;
    private String url;


    public String getLocalFile() {
        return localFile;
    }

    public void setLocalFile(String localFile) {
        this.localFile = localFile;
    }

    public String getRemoteFile() {
        return remoteFile;
    }

    public void setRemoteFile(String remoteFile) {
        this.remoteFile = remoteFile;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public FtpUtils() {
        super();
    }

    /**
     * 初始化时进行连接
     *
     * @param server
     * @param username
     * @param password
     * @param port
     */
    public FtpUtils(String server, String username, String password, int port, String localFile, String remoteFile, String url, int flag) {
        this.server = server;
        this.username = username;
        this.password = password;
        this.port = port;
        this.localFile = localFile;
        this.remoteFile = remoteFile;
        this.flag = flag;
        this.url = url;
        ftp = new FTPClient();
        try {

            ftp.connect(this.server, this.port);
            ftp.login(this.username, this.password);
            // 文件类型,默认是ASCII
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);

            // 设置被动模式
            ftp.enterLocalPassiveMode();

            ftp.setConnectTimeout(60000);//连接超时为60秒  2000
            ftp.setDataTimeout(60000);   //设置传输超时时间为60秒
            ftp.setControlEncoding("UTF-8");
//			ftp.setBufferSize(1024);
            int replyCode = ftp.getReplyCode();
//			log.info("replayCode: "+replyCode);
            if ((!FTPReply.isPositiveCompletion(replyCode))) {
                closeFTPClient();
                ftp = null;
                log.error("链接ftp出错！");
                throw new Exception("登录FTP失败，请检查!Server:" + server);
            }
        } catch (SocketException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * @throws Exception
     */
    public void closeFTPClient() {
        try {
            this.closeFTPClient(this.ftp);
        } catch (Exception e) {
            log.error("关闭FTP链接失败！");
            log.error(e.getMessage());
        }

    }

    public void closeFTPClient(FTPClient ftp) throws Exception {

        try {
            if (ftp.isConnected())
                ftp.disconnect();
        } catch (Exception e) {
            throw new Exception("关闭FTP连接失败!");
        }
    }

    /**
     * @param remoteFoldPath
     */
    public void changeDirectory(String remoteFoldPath) {
        try {
            if (remoteFoldPath.length() > 1) {

                String[] sts = remoteFoldPath.split("/");
                for (String s : sts) {
                    boolean flag = ftp.changeWorkingDirectory(s);
                    if (!flag) {
                        ftp.makeDirectory(s);
                        ftp.changeWorkingDirectory(s);
                    }
                }

            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

    }

    /**
     * @param localFile  传过来的是绝对地址，是194服务器上的地址
     * @param remoteFile 20141203
     * @return
     */
    public boolean uploadFile(String server, String localFile, String remoteFile) {
        InputStream input = null;
        boolean result = false;
        try {
            File file = null;

            String HeadAdd = ResourceUtil.getHeadAdd();
            String headReaplace = ResourceUtil.getHeadReplace();
            String localPath;
            if (localFile.contains(HeadAdd)) {
                //地址包含前缀了，绝对地址
                localPath = localFile;
            } else {
                localPath = HeadAdd + localFile;
            }
//			String localPath=HeadAdd+localFile;
            // 传过来的是绝对地址，不用加前缀了
//			String localPath = localFile;

            file = new File(localPath);
            input = new FileInputStream(file);

			
			
			/*if(localPath.contains("hnrb")){
				String hnrbPath = OtherLocalGenerate(localPath);  
			    file = new File(hnrbPath);
			    input = new FileInputStream(file);
			}else{
				file=new File(localPath);
				input = new FileInputStream(file);
			}*/

            String name = file.getName();

            // remotePath现在为绝对地址，需要把前缀替换掉
            //remotePath为FTP的以控制好的地址   理论上headReaplace和headAdd是一样的
            String remotePath = remoteFile.replace(headReaplace, "");
            //20150330  163to4g
//			String remotePath = remoteFile.replace(httpsqs163Head, "");
//		    remotePath=remoteFile.replace(name, "");
            remotePath = remotePath.replace(name, "");
//		    log.info("remotePath:"+remotePath);
            changeDirectory(remotePath);

            result = ftp.storeFile(name, input);
            log.info("result: " + result);
            if (!result) {
                log.info("上传出错！" + "  本地地址:" + localFile + " 远程地址:" + remoteFile + "  错误代码：" + result);
//		    	appFile(server, localFile);
            }
//		    log.info("server:"+server+"是否上传成功:"+result+"路径:"+localFile);
            input.close();
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
//			appFile(server,localFile);
//			log.error(" "+e.getMessage()+"  本地地址:"+localFile+" 远程地址:"+remoteFile);
//			e.printStackTrace();
        }
        return result;
    }


    /**
     *
     */
    public void logout() {
        try {
            this.ftp.logout();
        } catch (IOException e) {
//			log.error("ftp logout 异常:" + this.server + e.getLocalizedMessage());
//			e.printStackTrace();
            log.error(e.getMessage(), e);
            if (this.ftp.isConnected()) {
                try {
                    this.ftp.disconnect();
                } catch (IOException f) {
                    log.error(e.getMessage(), e);
                }
            }
        } finally {
            if (this.ftp.isConnected()) {
                try {
                    this.ftp.disconnect();
                } catch (IOException f) {
                    log.error(f.getMessage(), f);
                }
            }
        }
    }

    /**
     * @param path
     * @return
     */
    public boolean delete3(String path) {
        String headReaplace = ResourceUtil.getHeadReplace();
        boolean flag = false;
        String path1 = path.replace(headReaplace, "");
        //20150330 163to4g
//		String path1 = path.replace(httpsqs163Head, "");
//		String path1=path.replace(httpsqs171Head, "");
        String[] sts = path1.split("/");
        String name = sts[sts.length - 1];
        path1 = path1.replace(name, "");
        try {
            ftp.changeWorkingDirectory(path1);
            flag = ftp.deleteFile(name);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
//		System.out.println("flag: "+flag);
        if (!flag) {
//			System.out.println(path);
            log.error("delete file fail,delete path " + path);
        }
        return flag;
    }


    public void uploadOrdelete() {
        try {
            if (flag == 1) {
                this.uploadFile(server, localFile, remoteFile);
                this.logout();
            }
            if (flag == 2) {
                this.delete3(url);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void run() {

        try {
            if (flag == 1) {
                this.uploadFile(server, localFile, remoteFile);
                //20150330  Broken pipe
                this.logout();
            }
            if (flag == 2) {
                this.delete3(url);
                this.logout();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

}
