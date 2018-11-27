package com.laiyy.batch.writer.itemwriterfile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laiyy.batch.itemreadermultifile.City;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

/**
 * @author laiyy
 * @date 2018/11/26 14:35
 * @description
 */
@Configuration
public class FileItemWriterConfig {


    @Bean
    public FlatFileItemWriter<City> cityItemWriter(){
        FlatFileItemWriter<City> writer = new FlatFileItemWriter<>();
        String path = "d:\\city.txt";
//        writer.setResource(new ClassPathResource("city.txt"));
        writer.setResource(new FileSystemResource(path));
        writer.setLineAggregator(new LineAggregator<City>() {
            @Override
            public String aggregate(City city) {
                try {
                    return new ObjectMapper().writeValueAsString(city);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return "";
            }
        });


        try {
            writer.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return writer;
    }

}
