//package com.laiyy.batch.itemreader;
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
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @author laiyy
// * @date 2018/11/16 9:37
// * @description
// */
//@Configuration
//public class ItemReaderDemo {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//
//    @Autowired
//    public ItemReaderDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//
//    @Bean
//    public Job itemReaderDemoJob() {
//        return jobBuilderFactory.get("itemReaderDemoJob")
//                .start(itemReaderDemoStep())
//                .build();
//    }
//
//    @Bean
//    public Step itemReaderDemoStep(){
//        return stepBuilderFactory.get("itemReaderDemoStep")
//                .<String, String>chunk(2)
//                .reader(myStringItemReader())
//                .writer( list -> {
//                    list.forEach(System.err::println);
//                })
//                .build();
//    }
//
//    @Bean
//    public MyStringItemReader myStringItemReader(){
//        List<String> data = Arrays.asList("Cat", "Dog", "Pig", "Duck");
//        return new MyStringItemReader(data);
//    }
//
//
//
//}
