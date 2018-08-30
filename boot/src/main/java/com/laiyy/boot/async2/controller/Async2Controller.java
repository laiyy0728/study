package com.laiyy.boot.async2.controller;

import com.laiyy.boot.async2.service.SyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

/**
 * @author laiyy
 * @date 2018/8/30 17:15
 * @description
 */
@RestController
@Slf4j
public class Async2Controller {

    @Autowired
    private SyncService syncService;

    @GetMapping(value = "async")
    public String doAsync() throws Exception{
        long start = System.currentTimeMillis();
        log.info(">>>>>>>>>>>>> 方法执行开始：{}" , start);
        // 调用同步方法
        syncService.syncEvent();
        long syncTime = System.currentTimeMillis();
        log.info(">>>>>>>>>>>>>>>>> 同步方法用时：{}", (syncTime - start));

        // 调用异步方法
        syncService.asyncEvent();
        long asyncTime = System.currentTimeMillis();
        log.info(">>>>>>>>>>>>>> 异步方法用时：{}", (asyncTime - syncTime));
        log.info(">>>>>>>>>>>> 方法执行完成：{}", asyncTime);
        return "async!";
    }

    @GetMapping(value = "future")
    public String future() throws Exception{
        long start = System.currentTimeMillis();
        log.info("方法执行开始：{}", start);
        //调用同步方法
        syncService.syncEvent();
        long syncTime = System.currentTimeMillis();
        log.info("同步方法用时：{}", syncTime - start);
        //调用异步方法
        Future<String> doFutrue = syncService.future();
        while(true) {
            //判断异步任务是否完成
            if(doFutrue.isDone()) {
                break;
            }
            Thread.sleep(100);
        }
        long asyncTime = System.currentTimeMillis();
        log.info("异步方法用时：{}", asyncTime - syncTime);
        log.info("方法执行完成：{}!",asyncTime);
        return "async!!!";
    }
}
