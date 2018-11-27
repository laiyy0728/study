//package com.laiyy.batch.itemreaderxml;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.xml.StaxEventItemReader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.oxm.xstream.XStreamMarshaller;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author laiyy
// * @date 2018/11/26 9:51
// * @description
// */
//@Configuration
//public class XmlItemReaderDemo {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    private final ItemWriter<Label> xmlFileWriter;
//
//    @Autowired
//    public XmlItemReaderDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ItemWriter<Label> xmlFileWriter) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//        this.xmlFileWriter = xmlFileWriter;
//    }
//
//
//    @Bean
//    public Job xmlItemReaderDemoJob() {
//        return jobBuilderFactory.get("xmlItemReaderDemoJob")
//                .start(xmlItemReaderDemoStep())
//                .build();
//    }
//
//
//    @Bean
//    public Step xmlItemReaderDemoStep() {
//        return stepBuilderFactory.get("xmlItemReaderDemoStep")
//                .<Label, Label>chunk(3)
//                .reader(xmlFileReader())
//                .writer(xmlFileWriter)
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public StaxEventItemReader<Label> xmlFileReader(){
//        StaxEventItemReader<Label> reader = new StaxEventItemReader<>();
//        reader.setResource(new ClassPathResource("label.xml"));
//
//        // 指定需要处理的根标签
//        reader.setFragmentRootElementName("RECORD");
//
//        // 把读取到的 xml 格式转为 Label
//        XStreamMarshaller unmarshaller = new XStreamMarshaller();
//        Map<String, Class> map = new HashMap<>(1);
//        map.put("RECORD", Label.class);
//        unmarshaller.setAliases(map);
//
//        reader.setUnmarshaller(unmarshaller);
//        return reader;
//    }
//
//}
