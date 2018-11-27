//package com.laiyy.batch.writer.itemprocessor;
//
//import com.laiyy.batch.itemreadermultifile.City;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.listener.CompositeItemProcessListener;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.support.CompositeItemProcessor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author laiyy
// * @date 2018/11/26 15:52
// * @description
// */
//@Configuration
//public class ItemProcessorDemo {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    private ItemReader<City> multiFileReader;
//
//    @Autowired
//    private ItemWriter<City> cityItemWriter;
//
//    @Autowired
//    private ItemProcessor<City, City> nameLowerProcessor;
//
//    @Autowired
//    private ItemProcessor<City, City> idFiltterProcessor;
//
//    @Bean
//    public Job itemProcessorJob() {
//        return jobBuilderFactory.get("itemProcessorJob2")
//                .start(itemProcessorStep())
//                .build();
//    }
//
//    @Bean
//    public Step itemProcessorStep() {
//        return stepBuilderFactory.get("itemProcessorStep2")
//                .<City, City>chunk(10)
//                .reader(multiFileReader)
//                .processor(processListener())
//                .writer(cityItemWriter)
//                .build();
//    }
//
//    @Bean
//    public CompositeItemProcessor<City, City> processListener() {
//        CompositeItemProcessor<City, City> processor = new CompositeItemProcessor<>();
//        List<ItemProcessor<City, City>> list = new ArrayList<>(2);
//        list.add(nameLowerProcessor);
//        // 数据过滤
//        list.add(idFiltterProcessor);
//        processor.setDelegates(list);
//        return processor;
//    }
//
//}
