package ftp_jar;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by laiyy
 * Date 2017/8/2.
 */
public class MainRunner {

    public static void main(String[] args) {
        ScheduledExecutorService schService = Executors.newScheduledThreadPool(10);
        schService.scheduleWithFixedDelay(new GennerHtmlTask(), 2000L, 2000L, TimeUnit.MILLISECONDS);
        schService.scheduleWithFixedDelay(new FileSyncFileTask(), 3000L, 2000L, TimeUnit.MILLISECONDS);
    }

}
