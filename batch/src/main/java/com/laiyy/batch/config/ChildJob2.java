//package com.laiyy.batch.config;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
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
// * @date 2018/11/15 16:49
// * @description
// */
//@Configuration
//public class ChildJob2 {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    public ChildJob2(StepBuilderFactory stepBuilderFactory, JobBuilderFactory jobBuilderFactory) {
//        this.stepBuilderFactory = stepBuilderFactory;
//        this.jobBuilderFactory = jobBuilderFactory;
//    }
//
//
//    @Bean
//    public Job childJobTwo(){
//        return jobBuilderFactory.get("childJobTwo")
//                .start(childJobStep2())
//                .next(childJobStep3())
//                .build();
//    }
//
//    @Bean
//    public Step childJobStep3() {
//        return stepBuilderFactory.get("childJobStep3")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("childJobStep3");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//
//    @Bean
//    public Step childJobStep2() {
//        return stepBuilderFactory.get("childJobStep2")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("childJobStep2");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//
//}
