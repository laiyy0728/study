//package com.laiyy.batch.error;
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
//import java.util.Map;
//
///**
// * @author laiyy
// * @date 2018/11/26 16:11
// * @description
// */
//@Configuration
//public class ErrorDemo {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job errorJob() {
//        return jobBuilderFactory.get("errorJob")
//                .start(errorStep1())
//                .next(errorStep2())
//                .build();
//    }
//
//    @Bean
//    public Step errorStep1() {
//        return stepBuilderFactory.get("errorStep1")
//                .tasklet(errorTasklet()).build();
//    }
//
//
//    @Bean
//    public Step errorStep2() {
//        return stepBuilderFactory.get("errorStep2")
//                .tasklet(errorTasklet()).build();
//    }
//
//    @Bean
//    public Tasklet errorTasklet() {
//        return new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                Map<String, Object> context = chunkContext.getStepContext().getStepExecutionContext();
//                if (context.containsKey("laiyy")){
//                    System.out.println("yoyoyo");
//                    return RepeatStatus.FINISHED;
//                }
//                System.out.println(" yo ho !");
//                chunkContext.getStepContext().getStepExecution().getExecutionContext().put("laiyy", "ya ho !");
//                throw new RuntimeException("出错了！");
//            }
//        };
//    }
//
//}
