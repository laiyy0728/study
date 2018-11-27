//package com.laiyy.batch.config;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.job.flow.JobExecutionDecider;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author laiyy
// * @date 2018/11/15 16:10
// * @description
// */
//@Configuration
//@EnableBatchProcessing
//public class DeciderDemo {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    public DeciderDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    /**
//     * 创建任务2
//     */
//    @Bean
//    public Job deciderDemoJob(){
//        return jobBuilderFactory.get("deciderDemoJob")
//                .start(deciderDemoStep1())
//                .next(myDecider())
//                // 如果决策器返回的是 even，进入 step2
//                .from(myDecider()).on("even").to(deciderDemoStep2())
//                // 如果决策器返回的是 odd，进入 step3
//                .from(myDecider()).on("odd").to(deciderDemoStep3())
//                // 由于先执行的是 step3，那么无论决策器返回值是什么都重新进入决策器，即：进入 next(myDecider())，这时会进入 step2 执行。
//                // 如果不加这句，则只会执行 step3，不会执行 step2
//                .from(deciderDemoStep3()).on("*").to(myDecider())
//                .end().build();
//    }
//
//
//    /**
//     * 决策器
//     */
//    @Bean
//    public JobExecutionDecider myDecider(){
//        return new MyDecider();
//    }
//
//
//
//    @Bean
//    public Step deciderDemoStep3() {
//        return stepBuilderFactory.get("deciderDemoStep3")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("odd");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//
//    @Bean
//    public Step deciderDemoStep2() {
//        return stepBuilderFactory.get("deciderDemoStep2")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("even");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//
//    @Bean
//    public Step deciderDemoStep1() {
//        return stepBuilderFactory.get("deciderDemoStep1")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("deciderDemoStep1");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//
//}
