//package com.laiyy.batch.config;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author laiyy
// * @date 2018/11/15 11:09
// * @description 使用 @EnableBatchProcessing 启用批处理
// */
//@Configuration
//@EnableBatchProcessing
//public class JobConfiguration {
//
//    /**
//     * 注入 Job 工厂
//     */
//    private final JobBuilderFactory jobBuilderFactory;
//
//    /**
//     * 注入 Step 工厂
//     * 任务的执行由 Step 决定，一个任务有多个 Step
//     */
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    public JobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    @Bean
//    public Job helloWorldJob(){
//        return jobBuilderFactory
//                // 任务名称
//                .get("helloWorldJob")
//                // 任务执行 Step
//                .start(helloWorldStep())
//                // 构建
//                .build();
//    }
//
//    @Bean
//    public Step helloWorldStep(){
//        return stepBuilderFactory
//                // 步骤名称
//                .get("helloWorldStep")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        // 执行的功能
//                        System.out.println("helloWorldStep!");
//                        // RepeatStatus.FINISHED ：任务结束
//                        // RepeatStatus.CONTINUABLE ：等待
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//}
