//package com.laiyy.batch.writer.itemwriter;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.ItemReader;
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
// * @date 2018/11/26 11:44
// * @description
// */
//@Configuration
//public class ItemWriterDemo {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    private final ItemWriter<String> myWriter;
//
//    @Autowired
//    public ItemWriterDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ItemWriter<String> myWriter) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//        this.myWriter = myWriter;
//    }
//
//
//    @Bean
//    public Job itemWriterDemoJob(){
//        return jobBuilderFactory.get("itemWriterDemoJob")
//                .start(itemWriterDemoStep())
//                .build();
//    }
//
//    @Bean
//    public Step itemWriterDemoStep(){
//        return stepBuilderFactory.get("itemWriterDemoStep")
//                .<String, String>chunk(10)
//                .reader(myReader())
//                .writer(myWriter)
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public ItemReader<? extends String> myReader() {
//        List<String> items = new ArrayList<>();
//        for (int index = 0; index < 50; index++){
//            items.add("Java:" + index);
//        }
//        return new ListItemReader<>(items);
//    }
//}
