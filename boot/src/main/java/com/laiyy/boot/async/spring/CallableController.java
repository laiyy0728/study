package com.laiyy.boot.async.spring;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;

/**
 * @author laiyy
 * @date 2018/8/30 16:38
 * @description
 */
@RestController
@Slf4j
public class CallableController {

    @Autowired
    private TaskExecutor taskExecutor;

    @GetMapping(value = "web-async")
    public WebAsyncTask<String> webAsyncTask(){
        log.info(">>>>>>>>>>>>>>> 外部线程：" + Thread.currentThread().getName());

        WebAsyncTask<String> result = new WebAsyncTask<>(60 * 1000L, new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info(">>>>>>>>>>>>>>>> 内部线程：" + Thread.currentThread().getName());
                return "WebAsyncTask";
            }
        });

        result.onTimeout(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "time out";
            }
        });

        result.onCompletion(new Runnable() {
            @Override
            public void run() {
                log.info(">>>>>>>>>>>> 执行结束！");
            }
        });

        return result;
    }

    @GetMapping(value = "deferred-result")
    public DeferredResult<String> deferredResult(){
        log.info(">>>>>>>>>>> 外部线程：" + Thread.currentThread().getName());
        // 设置超时时间
        DeferredResult<String> result = new DeferredResult<>(60 * 1000L);
        // 超时处理
        result.onTimeout(new Runnable() {
            @Override
            public void run() {
                log.error(">>>>>>>>> 超时了！");
                result.setResult("超时了！！");
            }
        });

        result.onCompletion(new Runnable() {
            @Override
            public void run() {
                log.info(">>>>>>>>>> 执行完成");
            }
        });

        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                log.info(">>>>>>>>>>>>>>>> 内部线程：" + Thread.currentThread().getName());
                result.setResult("调用完成！");
            }
        });
        return result;
    }

    @GetMapping(value = "callable")
    public Callable<String> callable(){
        log.info(">>>>>>>>>>>> 外部线程：" + Thread.currentThread().getName());
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info(">>>>>>>>>>>> 内部线程：" + Thread.currentThread().getName());
                return "callable!";
            }
        };
    }

}
