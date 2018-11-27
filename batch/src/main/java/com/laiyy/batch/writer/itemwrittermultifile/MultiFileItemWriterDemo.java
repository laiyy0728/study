//package com.laiyy.batch.writer.itemwrittermultifile;
//
//import com.laiyy.batch.itemreadermultifile.City;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemStreamWriter;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author laiyy
// * @date 2018/11/26 15:27
// * @description
// */
//@Configuration
//public class MultiFileItemWriterDemo {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    private final ItemReader<City> multiFileReader;
//
//    private final ItemWriter<City> multiFileWriter;
//
//    private final ItemStreamWriter<City> xmlItemWriter;
//
//    private final ItemStreamWriter<City> cityItemWriter;
//
//    @Autowired
//    public MultiFileItemWriterDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ItemReader<City> multiFileReader, @Qualifier("multiFileWriter") ItemWriter<City> multiFileWriter, ItemStreamWriter<City> xmlItemWriter, ItemStreamWriter<City> cityItemWriter) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//        this.multiFileReader = multiFileReader;
//        this.multiFileWriter = multiFileWriter;
//        this.xmlItemWriter = xmlItemWriter;
//        this.cityItemWriter = cityItemWriter;
//    }
//
//    @Bean
//    public Job multiFileItemWriterJob(){
//        return jobBuilderFactory.get("multiFileItemWriterJob1")
//                .start(multiFileItemWriterStep())
//                .build();
//    }
//
//    @Bean
//    public Step multiFileItemWriterStep(){
//        return stepBuilderFactory.get("multiFileItemWriterStep2")
//                .<City, City>chunk(10)
//                .reader(multiFileReader)
//                .writer(multiFileWriter)
//                .stream(xmlItemWriter)
//                .stream(cityItemWriter)
//                .build();
//    }
//}
