package ftp_jar;

import java.util.ResourceBundle;

/**
 * Created by wh on 2017/6/30.
 */
public class ResourcesUtil {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("config");

    public static final String redisip() {
        return bundle.getString("redis.ip");
    }

    public static final String redisport() {
        return bundle.getString("redis.port");
    }

    public static final String redispwd() {
        return bundle.getString("redis.pwd");
    }

    public static final String getPath() {
        return bundle.getString("path");
    }

    public static String getNewsSuffix() {
        return bundle.getString("newsSuffix");
    }

    public static String getTaskName() {
        return bundle.getString("taskName");
    }

    public static String getFileTask(){
        return bundle.getString("fileTask");
    }


    public static String getFirstDir() {
        return bundle.getString("firstDir");
    }

    public static String getSecondDir() {
        return bundle.getString("secondDir");
    }

    public static String getServices() {
        return bundle.getString("services");
    }

    /**
     * @return 获得图片前缀
     */
    public static String getPicUrl() {
        return bundle.getString("picUrl");
    }

    /**
     * 文件服务器路径
     */
    public static String getFileUrl(){
        return bundle.getString("fileUrl");
    }
}
