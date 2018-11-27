package com.laiyy.batch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * @author laiyy
 * @date 2018/11/15 17:27
 * @description
 *
 * 接口实现监听
 *
 */
public class MyJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        // 在 job 执行之前执行
        System.out.println("Job 执行之前，Job 名称：" + jobExecution.getJobInstance().getJobName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        // 在 job 执行之后执行
        System.out.println("Job 执行之后，Job 名称：" + jobExecution.getJobInstance().getJobName());
    }
}
