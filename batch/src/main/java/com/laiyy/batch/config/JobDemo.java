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
// * @date 2018/11/15 15:06
// * @description
// */
//@Configuration
//@EnableBatchProcessing
//public class JobDemo {
//
//    private static final String COMPLETED = "COMPLETED";
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    public JobDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    @Bean
//    public Job jobDemoJob() {
//        return jobBuilderFactory.get("jobDemo")
////                .start(stepDemo1())
////                .next(stepDemo2())
////                .next(stepDemo3())
////                .next(stepDemo4())
////                .build();
//                // 从 demo1, 开始，当 结束后，到 demo2
//                .start(stepDemo1()).on(COMPLETED).to(stepDemo2())
//                // 从 demo2 开始，当结束后，到 demo3
//                .from(stepDemo2()).on(COMPLETED).to(stepDemo3())
//                .from(stepDemo3()).on(COMPLETED).to(stepDemo4())
//                // 从 demo4 开始，到结束
//                .from(stepDemo4()).end().build();
//    }
//
//    @Bean
//    public Step stepDemo4() {
//        return stepBuilderFactory.get("stepDemo4")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println(" stepDemo 4");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//    @Bean
//    public Step stepDemo3() {
//        return stepBuilderFactory.get("stepDemo3")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println(" stepDemo 3");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//    @Bean
//    public Step stepDemo2() {
//        return stepBuilderFactory.get("stepDemo2")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println(" stepDemo 2");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//    @Bean
//    public Step stepDemo1() {
//        return stepBuilderFactory.get("stepDemo1")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println(" stepDemo 1");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//}
