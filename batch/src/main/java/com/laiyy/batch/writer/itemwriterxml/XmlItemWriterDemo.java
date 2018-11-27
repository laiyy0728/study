package com.laiyy.batch.writer.itemwriterxml;

import com.laiyy.batch.itemreadermultifile.City;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author laiyy
 * @date 2018/11/26 14:52
 * @description
 */
//@Configuration
public class XmlItemWriterDemo {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final ItemReader<City> cityItemReader;

    private final ItemWriter<City> xmlItemWriter;

    @Autowired
    public XmlItemWriterDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ItemReader<City> cityItemReader, ItemWriter<City> xmlItemWriter) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.cityItemReader = cityItemReader;
        this.xmlItemWriter = xmlItemWriter;
    }

    @Bean
    public Job xmlItemWriterJob(){
        return jobBuilderFactory.get("xmlItemWriterJob")
                .start(xmlItemWriterStep())
                .build();
    }

    @Bean
    public Step xmlItemWriterStep(){
        return stepBuilderFactory.get("xmlItemWriterStep")
                .<City, City>chunk(10)
                .reader(cityItemReader)
                .writer(xmlItemWriter)
                .build();
    }


}
