//package com.laiyy.batch.config;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.JobStepBuilder;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
///**
// * @author laiyy
// * @date 2018/11/15 16:53
// * @description Job 嵌套
// */
//@Configuration
//public class NestedDemo {
//
//    /**
//     * 注入 子 job
//     */
//
//    private final Job childJobTwo;
//
//    private final Job childJobOne;
//
//    /**
//     * job 启动器
//     */
//    private final JobLauncher jobLauncher;
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    public NestedDemo(JobBuilderFactory jobBuilderFactory, Job childJobTwo, Job childJobOne, JobLauncher jobLauncher) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.childJobTwo = childJobTwo;
//        this.childJobOne = childJobOne;
//        this.jobLauncher = jobLauncher;
//    }
//
//    @Bean
//    public Job parentJob(JobRepository repository, PlatformTransactionManager transactionManager){
//        return jobBuilderFactory.get("parentJob")
//                .start(childJob1(repository, transactionManager))
//                .next(childJob2(repository, transactionManager))
//                .build();
//    }
//
//    /**
//     * 返回的是 Job 类型的 Step，特殊的 Step
//     */
//    private Step childJob2(JobRepository repository, PlatformTransactionManager transactionManager){
//        // 将 Job 类型转换为 Step 类型
//        return new JobStepBuilder(new StepBuilder("childJob2"))
//                // 子 Job
//                .job(childJobTwo)
//                // 启动器
//                .launcher(jobLauncher)
//                // 持久化
//                .repository(repository)
//                // 事务管理
//                .transactionManager(transactionManager)
//                .build();
//    }
//
//    private Step childJob1(JobRepository repository, PlatformTransactionManager transactionManager) {
//        return new JobStepBuilder(new StepBuilder("childJob1"))
//                // 子 Job
//                .job(childJobOne)
//                // 启动器
//                .launcher(jobLauncher)
//                // 持久化
//                .repository(repository)
//                // 事务管理
//                .transactionManager(transactionManager)
//                .build();
//    }
//
//}
