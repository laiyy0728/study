package com.laiyy.batch.joblauncher;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laiyy
 * @date 2018/11/27 9:53
 * @description
 */
@RestController
public class JobController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job jobLauncherDemoJob;

    @Autowired
    private JobOperator jobOperator;

    @GetMapping(value = "/job/{msg}")
    public String jobRun1(@PathVariable String msg){
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("msg", msg)
                .toJobParameters();

        // 启动任务，并把参数传给任务
        try {
            jobLauncher.run(jobLauncherDemoJob, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "job success";
    }

    @GetMapping(value = "/job2/{msg}")
    public String jobRun2(@PathVariable String msg){
//        jobOperator
        return "JOB SUCCESS";
    }

}
