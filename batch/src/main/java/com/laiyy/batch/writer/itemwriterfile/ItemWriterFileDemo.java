package com.laiyy.batch.writer.itemwriterfile;

import com.laiyy.batch.itemreadermultifile.City;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author laiyy
 * @date 2018/11/26 14:06
 * @description
 */
//@Configuration
public class ItemWriterFileDemo {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final ItemReader<City> cityItemReader;

    private final ItemWriter<City> cityItemWriter;

    @Autowired
    public ItemWriterFileDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ItemReader<City> cityItemReader, ItemWriter<City> cityItemWriter) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.cityItemReader = cityItemReader;
        this.cityItemWriter = cityItemWriter;
    }

    @Bean
    public Job itemWriterFileJob(){
        return jobBuilderFactory.get("itemWriterFileJob2")
                .start(itemWriterFileStep())
                .build();

    }

    @Bean
    public Step itemWriterFileStep(){
        return stepBuilderFactory.get("itemWriterFileStep2")
                .<City, City>chunk(10)
                .reader(cityItemReader)
                .writer(cityItemWriter)
                .build();
    }






}
