package ftp_jar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by laiyy
 * Date 2017/8/1.
 */
public class FileFtp {

    private static final Logger logger = LoggerFactory.getLogger(FileFtp.class);

    private static FileFtp fileFtp;

    private static Integer lock = 0;

    private ExecutorService pool;

    private static final String[] SERVICE = ResourcesUtil.getServices().split(",");

    private static final String SERVER = SERVICE[0];
    private static final String USERNAME = SERVICE[1];
    private static final String PASSWORD = SERVICE[2];

    private FileFtp() {
        pool = Executors.newCachedThreadPool();
    }

    public static FileFtp getInstance() {
        if (fileFtp == null) {
            synchronized (lock) {
                if (fileFtp == null) {
                    fileFtp = new FileFtp();
                }
            }
        }
        return fileFtp;
    }

    public void uploadHtml( String remoteUrl, String fileName, String html) {
        FtpUtils ftpUtils = new FtpUtils(SERVER, USERNAME, PASSWORD, "", remoteUrl, null, fileName, html, 1);
        pool.execute(ftpUtils);
    }

    public void uploadFile(InputStream inputStream,  String remoteUrl, String fileName) {
//        File file = new File(localUrl + File.separator + fileName);
        try {
//            FileInputStream inputStream = new FileInputStream(file);
            FtpUtils ftpUtils = new FtpUtils(SERVER, USERNAME, PASSWORD, "", remoteUrl, inputStream, fileName, "", 3);
            pool.execute(ftpUtils);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(String remoteUrl, String fileName) {
        FtpUtils FtpUtils = new FtpUtils(SERVER, USERNAME, PASSWORD, "", remoteUrl, null, fileName, "", 2);
        pool.execute(FtpUtils);
    }

}
