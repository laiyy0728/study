//package com.laiyy.batch.writer.itemwriterxml;
//
//import com.laiyy.batch.itemreadermultifile.City;
//import org.springframework.batch.item.xml.StaxEventItemWriter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.oxm.xstream.XStreamMarshaller;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author laiyy
// * @date 2018/11/26 14:54
// * @description
// */
////@Configuration
//public class XmlWriterConfig {
//
//    @Bean
//    public StaxEventItemWriter<City> xmlItemWriter(){
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
//}
