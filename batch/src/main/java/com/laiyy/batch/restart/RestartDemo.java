//package com.laiyy.batch.restart;
//
//import com.laiyy.batch.itemreadermultifile.City;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author laiyy
// * @date 2018/11/26 11:29
// * @description
// */
//@Configuration
//public class RestartDemo {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    private final ItemWriter<City> restartWriter;
//
//    private final ItemReader<City> restartReader;
//
//
//    @Autowired
//    public RestartDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ItemWriter<City> restartWriter, ItemReader<City> restartReader) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//        this.restartWriter = restartWriter;
//        this.restartReader = restartReader;
//    }
//
//    @Bean
//    public Job restartDemoJob(){
//        return jobBuilderFactory.get("restartDemoJob")
//                .start(restartDemoStep())
//                .build();
//    }
//
//    @Bean
//    public Step restartDemoStep(){
//        return stepBuilderFactory.get("restartDemoStep")
//                .<City, City>chunk(10)
//                .reader(restartReader)
//                .writer(restartWriter)
//                .build();
//    }
//
//}
