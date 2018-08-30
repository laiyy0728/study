package com.laiyy.boot.async2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @author laiyy
 * @date 2018/8/30 17:13
 * @description
 */
@Component
@Slf4j
public class SyncService {

    @Async
    public void asyncEvent() throws Exception{
        System.out.println(Thread.currentThread().getName());
        // 休眠 1 秒
        Thread.sleep(1000);
        log.info(">>>>>>>>>>>> 异步输出：{} ", System.currentTimeMillis());
    }

    public void syncEvent() throws Exception{
        System.out.println(Thread.currentThread().getName());
        // 休眠 1 秒
        Thread.sleep(1000);
        log.info(">>>>>>>>>>>> 同步输出：{} ", System.currentTimeMillis());
    }

    /**
     * threadPoolTaskExecutor：自定义线程池 bean 名称
     */
    @Async("threadPoolTaskExecutor")
    public Future<String> future() throws Exception {
        System.out.println(Thread.currentThread().getName());
        // 休眠 1 秒
        Thread.sleep(1000);
        log.info(">>>>>>>>>>>> 同步输出：{} ", System.currentTimeMillis());
        return new AsyncResult<>("异步调用方法返回值");
    }

}
