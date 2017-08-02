package ftp_jar;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 生成静态文件的定时队列
 * Created by laiyy
 * Date 2017/7/18.
 */
public class GennerHtmlTask implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(GennerHtmlTask.class);

    private void gennerHtml() {
        String json = JedisUtils.getInstance().rpop(ResourcesUtil.getTaskName());
        if (StringUtils.isNotBlank(json)) {
            logger.info(" ------------------- 从队列中取到的队列值：" + json);
            String[] arr = json.split(",");
            if (arr.length == 3) {
                String fileName = arr[0];
                String path = arr[1];
                String option = arr[2];
                logger.info(" ------------------- 文件名：" + fileName + "， 文件路径：" + path + "， 文件操作：" + option);
                switch (option) {
                    case "add":
                        String key = ResourcesUtil.getNewsSuffix() + path + "/" + fileName;
                        logger.info(" ============================ 正在同步 FTP ： " + key);
                        String html = JedisUtils.getInstance().get(key);
                        FileFtp.getInstance().uploadHtml(path, fileName, html);
                        JedisUtils.getInstance().del(key);
                        logger.info(" ============================ 同步完成，已从 redis 删除");
                        break;
                    case "del":
                        logger.info("============================= 正在从 FTP 删除：" + path + "/" + fileName);
                        FileFtp.getInstance().deleteFile(path, fileName);
                        logger.info(" ============================ 已从 FTP 删除：" + path + "/" + fileName);
                        break;
                    default:
                        break;
                }
            }
        }
    }


    @Override
    public void run() {
        gennerHtml();
    }
}
