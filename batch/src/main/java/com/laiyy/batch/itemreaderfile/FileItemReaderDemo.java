//package com.laiyy.batch.itemreaderfile;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.FlatFileItemWriter;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.mapping.FieldSetMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.batch.item.file.transform.FieldSet;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.validation.BindException;
//
///**
// * @author laiyy
// * @date 2018/11/16 10:16
// * @description
// */
//@Configuration
//public class FileItemReaderDemo {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    private final ItemWriter<Template> flatFileItemWriter;
//
//    @Autowired
//    public FileItemReaderDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ItemWriter<Template> flatFileItemWriter) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//        this.flatFileItemWriter = flatFileItemWriter;
//    }
//
//
//    @Bean
//   public Job fileItemReaderDemoJob() {
//        return jobBuilderFactory.get("fileItemReaderDemoJob")
//                .start(fileItemReaderDemoStep())
//                .build();
//    }
//
//    @Bean
//    public Step fileItemReaderDemoStep(){
//        return stepBuilderFactory.get("fileItemReaderDemoStep")
//                .<Template, Template>chunk(5)
//                .reader(flatFileReader())
//                .writer(flatFileItemWriter)
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public FlatFileItemReader<Template> flatFileReader() {
//        FlatFileItemReader<Template> reader = new FlatFileItemReader<>();
//        reader.setResource(new ClassPathResource("data.txt"));
//        // 跳过第几行
//        reader.setLinesToSkip(0);
//
//
//        // 解析数据
//        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
//        tokenizer.setNames("id", "date", "name", "siteId", "status", "type", "userId", "username", "share", "markId");
//        // 解析后的数据映射为对象
//        DefaultLineMapper<Template> mapper = new DefaultLineMapper<>();
//        mapper.setLineTokenizer(tokenizer);
//
//        mapper.setFieldSetMapper(new FieldSetMapper<Template>() {
//            @Override
//            public Template mapFieldSet(FieldSet fieldSet) throws BindException {
//                Template template = new Template();
//                template.setId(fieldSet.readInt("id"));
//                template.setDate(fieldSet.readString("date"));
//                template.setName(fieldSet.readString("name"));
//                template.setSiteId(fieldSet.readString("siteId"));
//                template.setStatus(fieldSet.readInt("status"));
//                template.setType(fieldSet.readInt("type"));
//                template.setUserId(fieldSet.readInt("userId"));
//                template.setUsername(fieldSet.readString("username"));
//                template.setShare(fieldSet.readInt("share"));
//                template.setMarkId(fieldSet.readString("markId"));
//                return template;
//            }
//        });
//        // 数据校验
//        mapper.afterPropertiesSet();
//        // 绑定映射
//        reader.setLineMapper(mapper);
//        return reader;
//    }
//
//}
