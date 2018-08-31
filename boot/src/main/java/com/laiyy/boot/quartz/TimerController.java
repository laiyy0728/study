package com.laiyy.boot.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author laiyy
 * @date 2018/8/31 9:50
 * @description
 */
@RestController
@Slf4j
public class TimerController {

    @GetMapping(value = "scheduledExecutorService")
    public String scheduledExecutorService(){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info(">>>>>>>>>>>>>>>>>>scheduledExecutorService定时任务执行：" + new Date());
            }
        }, 1, 1, TimeUnit.SECONDS);
        log.info(">>>>>>>>>>>>>>>>>>>scheduledExecutorService定时任务启动：" + new Date());
        return "scheduledExecutorService";
    }

    @GetMapping(value = "/timer")
    public String doTimer(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            // 要执行的任务
            @Override
            public void run() {
                log.info(">>>>>>>>>>>>>Timer 定时任务启动：" + new Date());
            }
            // 延迟 1 秒，执行间隔 1 秒
        }, 1000, 1000);
        return "timer";
    }


}
