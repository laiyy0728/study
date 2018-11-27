package com.laiyy.batch.skiplistener;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author laiyy
 * @date 2018/11/27 9:34
 * @description
 */
@Configuration
public class SkipListenerDemo {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final ItemWriter<String> skipItemWriter;

    private final ItemProcessor<String, String> skipItemProcessor;

    private final SkipListener<String, String> skipListener;

    @Autowired
    public SkipListenerDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ItemWriter<String> skipItemWriter, ItemProcessor<String, String> skipItemProcessor, SkipListener<String, String> skipListener) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.skipItemWriter = skipItemWriter;
        this.skipItemProcessor = skipItemProcessor;
        this.skipListener = skipListener;
    }

    @Bean
    public Job skipListenerJob(){
        return jobBuilderFactory.get("skipListenerJob")
                .start(skipListenerStep())
                .build();
    }

    @Bean
    public Step skipListenerStep(){
        return stepBuilderFactory.get("skipListenerStep")
                .<String, String>chunk(10)
                .reader(reader())
                .writer(skipItemWriter)
                .processor(skipItemProcessor)
                .faultTolerant()
                .skip(RuntimeException.class)
                .listener(skipListener)
                .skipLimit(10)
                .build();
    }


    @Bean
    @StepScope
    public ListItemReader<String> reader(){
        List<String> items = new ArrayList<>();
        for (int index = 0; index< 60; index++){
            items.add(""+index);
        }
        return new ListItemReader<>(items);
    }

}
