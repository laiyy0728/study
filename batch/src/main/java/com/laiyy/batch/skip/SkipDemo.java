//package com.laiyy.batch.skip;
//
//import org.omg.SendingContext.RunTime;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.support.ListItemReader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author laiyy
// * @date 2018/11/26 16:29
// * @description
// */
//@Configuration
//public class SkipDemo {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    private final ItemWriter<String> retryItemWriter;
//
//    private final ItemProcessor<String, String> retryItemProcessor;
//
//    @Autowired
//    public SkipDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ItemWriter<String> retryItemWriter, ItemProcessor<String, String> retryItemProcessor) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//        this.retryItemWriter = retryItemWriter;
//        this.retryItemProcessor = retryItemProcessor;
//    }
//
//    @Bean
//    public Job skipDemoJob(){
//        return jobBuilderFactory.get("skipDemoJob")
//                .start(skipDemoStep())
//                .build();
//    }
//
//    @Bean
//    public Step skipDemoStep(){
//        return stepBuilderFactory.get("skipDemoStep")
//                .<String, String>chunk(10)
//                .reader(reader())
//                .processor(retryItemProcessor)
//                .writer(retryItemWriter)
//                // 容错
//                .faultTolerant()
//                // 跳过
//                .skip(RuntimeException.class)
//                // 跳过次数
//                .skipLimit(10)
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public ListItemReader<String> reader(){
//        List<String> items = new ArrayList<>();
//        for (int index = 0; index< 60; index++){
//            items.add(""+index);
//        }
//        return new ListItemReader<>(items);
//    }
//}
