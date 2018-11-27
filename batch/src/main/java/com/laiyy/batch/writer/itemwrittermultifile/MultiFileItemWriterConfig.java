//package com.laiyy.batch.writer.itemwrittermultifile;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.laiyy.batch.itemreadermultifile.City;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.file.FlatFileItemWriter;
//import org.springframework.batch.item.file.transform.LineAggregator;
//import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
//import org.springframework.batch.item.support.CompositeItemWriter;
//import org.springframework.batch.item.xml.StaxEventItemWriter;
//import org.springframework.classify.Classifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.oxm.xstream.XStreamMarshaller;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author laiyy
// * @date 2018/11/26 15:30
// * @description
// */
//@Configuration
//public class MultiFileItemWriterConfig {
//
//    @Bean
//    public StaxEventItemWriter<City> xmlItemWriter() {
//        StaxEventItemWriter<City> writer = new StaxEventItemWriter<>();
//
//        XStreamMarshaller marshaller = new XStreamMarshaller();
//        Map<String, Class> aliases = new HashMap<>(1);
//        aliases.put("city", City.class);
//        marshaller.setAliases(aliases);
//
//        writer.setMarshaller(marshaller);
//
//        String path = "d:\\city.xml";
//        writer.setResource(new FileSystemResource(path));
//
//        try {
//            writer.afterPropertiesSet();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return writer;
//    }
//
//
//    @Bean
//    public FlatFileItemWriter<City> cityItemWriter() {
//        FlatFileItemWriter<City> writer = new FlatFileItemWriter<>();
//        String path = "d:\\city.txt";
//        writer.setResource(new FileSystemResource(path));
//        writer.setLineAggregator(new LineAggregator<City>() {
//            @Override
//            public String aggregate(City city) {
//                try {
//                    return new ObjectMapper().writeValueAsString(city);
//                } catch (JsonProcessingException e) {
//                    e.printStackTrace();
//                }
//                return "";
//            }
//        });
//
//        try {
//            writer.afterPropertiesSet();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return writer;
//    }
//
//
//    /**
//     * 实现数据分类到多个文件
//     */
//    @Bean
//    public ClassifierCompositeItemWriter<City> multiFileWriter() {
//        ClassifierCompositeItemWriter<City> writer = new ClassifierCompositeItemWriter<>();
//
//        writer.setClassifier(new Classifier<City, ItemWriter<? super City>>() {
//            @Override
//            public ItemWriter<? super City> classify(City city) {
//                // 按照 City 的 id 进行分类
//                if (city.getId() % 2 == 0){
//                    return cityItemWriter();
//                } else {
//                    return xmlItemWriter();
//                }
//            }
//        });
//        return writer;
//    }
//
//
//    /**
//     * 实现数据写入到多个文件
//     */
////    @Bean
////    public CompositeItemWriter<City> multiFileWriter() throws Exception {
////        CompositeItemWriter<City> writer = new CompositeItemWriter<>();
////        writer.setDelegates(Arrays.asList(cityItemWriter(), xmlItemWriter()));
////
////        writer.afterPropertiesSet();
////        return writer;
////    }
//
//
//}
