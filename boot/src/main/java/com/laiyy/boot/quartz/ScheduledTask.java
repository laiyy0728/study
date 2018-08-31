package com.laiyy.boot.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author laiyy
 * @date 2018/8/31 10:04
 * @description
 */
@Component
@Slf4j
public class ScheduledTask {

    /**
     * 自动扫描执行，每 5 秒执行一次
     */
    @Scheduled(fixedDelay = 5000, fixedRate = 5000)
    public void currentDate(){
        log.info(">>>>>>>>>>>>>>>>> Spring Task 定时任务执行：" + new Date());
    }

}
