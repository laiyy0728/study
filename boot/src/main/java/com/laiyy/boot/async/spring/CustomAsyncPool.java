package com.laiyy.boot.async.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author laiyy
 * @date 2018/8/30 16:40
 * @description
 */
@Configuration
public class CustomAsyncPool extends WebMvcConfigurationSupport {
    @Override
    protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        // 设置异步执行超时时间
        configurer.setDefaultTimeout(60 * 1000);
        // 设置线程池
        configurer.setTaskExecutor(threadPoolExecutor());
        // 超时处理
        configurer.registerCallableInterceptors(timeoutCallableProcessingInterceptor());
    }

    @Bean
    public TimeoutCallableProcessingInterceptor timeoutCallableProcessingInterceptor(){
        return new TimeoutCallableProcessingInterceptor();
    }

    /**
     * 配置线程池
     * @return 线程池
     */
    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 初始化 20 个线程
        taskExecutor.setCorePoolSize(20);
        // 最大 200 个线程
        taskExecutor.setMaxPoolSize(200);
        // 挤压 25 个线程
        taskExecutor.setQueueCapacity(25);
        // 最大保持 200 毫秒生存时间
        taskExecutor.setKeepAliveSeconds(200);
        taskExecutor.setThreadNamePrefix("callable-");
        // 线程拒绝策略，默认为 CallerRunsPolicy。 目前支持：CallerRunsPolicy、AbortPolicy
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }

}
