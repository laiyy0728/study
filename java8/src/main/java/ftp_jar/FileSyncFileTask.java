package ftp_jar;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by laiyy
 * Date 2017/7/27.
 */
public class FileSyncFileTask implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(FileSyncFileTask.class);

    @Override
    public void run() {
        syncFile();
    }

    private void syncFile() {
        String rpop = JedisUtils.getInstance().rpop(ResourcesUtil.getFileTask());
        if (StringUtils.isNotBlank(rpop)) {
            logger.info("Redis 中取到的文件信息：" + rpop);
            String[] split = rpop.split(",");
            if (split.length == 3) {
                String localFile = split[0];
                String dir = split[1];

                String fileName = split[2];
                File file = new File(localFile);
                InputStream inputStream = null;
                try {
                    if (file.exists()) {
                        logger.info("正在上传：" + fileName);
                        logger.info("上传到文件夹：" + dir);
                        logger.info("本地文件路径：" + localFile);
                        inputStream = new FileInputStream(file);
                        FileFtp.getInstance().uploadFile(inputStream, dir, fileName);
                    }
                } catch (Exception e) {
                    // 如果上传失败，重新放入队列
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    JedisUtils.getInstance().lpush(ResourcesUtil.getFileTask(), rpop);
                    logger.info("上传失败：" + e.getMessage());
                }
            }
        }
    }
}
