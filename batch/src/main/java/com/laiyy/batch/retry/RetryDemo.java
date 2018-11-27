//package com.laiyy.batch.retry;
//
//import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
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
//public class RetryDemo {
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
//    public RetryDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ItemWriter<String> retryItemWriter, ItemProcessor<String, String> retryItemProcessor) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//        this.retryItemWriter = retryItemWriter;
//        this.retryItemProcessor = retryItemProcessor;
//    }
//
//    @Bean
//    public Job retryDemoJob(){
//        return jobBuilderFactory.get("retryDemoJob")
//                .start(retryDemoStep())
//                .build();
//    }
//
//    @Bean
//    public Step retryDemoStep(){
//        return stepBuilderFactory.get("retryDemoStep")
//                .<String, String>chunk(10)
//                .reader(reader())
//                .processor(retryItemProcessor)
//                .writer(retryItemWriter)
//                // 容错
//                .faultTolerant()
//                // 发生哪个异常时进行重试
//                .retry(RuntimeException.class)
//                // 重试几次
//                .retryLimit(10)
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
