package com.laiyy.batch.writer.itemwriterdb;

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
 * @date 2018/11/26 11:44
 * @description
 */
//@Configuration
public class ItemWriterDbDemo {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final ItemReader<City> flatFileItemReader;

    private final ItemWriter<City> flatFileItemWriter;

    @Autowired
    public ItemWriterDbDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,ItemReader<City> flatFileItemReader, ItemWriter<City> flatFileItemWriter) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.flatFileItemReader = flatFileItemReader;
        this.flatFileItemWriter = flatFileItemWriter;
    }


    @Bean
    public Job itemWriterDemoJob(){
        return jobBuilderFactory.get("itemWriterDbDemoJob")
                .start(itemWriterDemoStep())
                .build();
    }

    @Bean
    public Step itemWriterDemoStep(){
        return stepBuilderFactory.get("itemWriterDbDemoStep")
                .<City, City>chunk(10)
                .reader(flatFileItemReader)
                .writer(flatFileItemWriter)
                .build();
    }

}
