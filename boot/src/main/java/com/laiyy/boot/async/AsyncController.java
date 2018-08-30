package com.laiyy.boot.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author laiyy
 * @date 2018/8/30 16:06
 * @description
 */
@RestController
@Slf4j
public class AsyncController {
    @GetMapping(value = "/servlet/orig")
    public void todo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 休眠
        Thread.sleep(100);
        response.getWriter().println("正常的请求返回");
    }
    @GetMapping(value = "/servlet/async")
    public void todoAsync(HttpServletRequest request, HttpServletResponse response) {
        AsyncContext asyncContext = request.startAsync();
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent asyncEvent) throws IOException {
                log.info(" 执行完成");
            }
            @Override
            public void onTimeout(AsyncEvent asyncEvent) throws IOException {
                log.info(" 超时了！");
            }
            @Override
            public void onError(AsyncEvent asyncEvent) throws IOException {
                log.info(" 发生错误");
            }
            @Override
            public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
                log.info(" 线程开始");
            }
        });
        // 设置超时时间
        asyncContext.setTimeout(200);
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    log.info(">>> 内部线程：" + Thread.currentThread().getName());
                    asyncContext.getRequest().setCharacterEncoding("UTF-8");
                    asyncContext.getResponse().setContentType("text/html; charset=UTF-8");
                    asyncContext.getResponse().getWriter().println("异步请求结果");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 执行完成，通知
                asyncContext.complete();
            }
        });
        // request 已经释放
        log.info(" 线程：" + Thread.currentThread().getName());
    }

}
