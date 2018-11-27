//package com.laiyy.batch.itemreadermultifile;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.MultiResourceItemReader;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.mapping.FieldSetMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.batch.item.file.transform.FieldSet;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.bind.BindException;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//
///**
// * @author laiyy
// * @date 2018/11/26 10:23
// * @description
// */
//@Configuration
//public class MultiFileItemReaderDemo {
//
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    private final ItemWriter<City> multiFileWriter;
//
//    @Value("classpath:file*.txt")
//    private Resource[] fileResources;
//
//
//    @Autowired
//    public MultiFileItemReaderDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ItemWriter<City> itemWriter) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//        this.multiFileWriter = itemWriter;
//    }
//
//
//    @Bean
//   public Job multiFileItemReaderJob() {
//        return jobBuilderFactory.get("multiFileItemReaderJob1")
//                .start(multiFileItemReaderDemoStep())
//                .build();
//    }
//
//    @Bean
//    public Step multiFileItemReaderDemoStep(){
//        return stepBuilderFactory.get("multiFileItemReaderDemoStep1")
//                .<City, City>chunk(5)
//                .reader(multiFileReader())
//                .writer(multiFileWriter)
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public MultiResourceItemReader<City> multiFileReader() {
//        MultiResourceItemReader<City> reader = new MultiResourceItemReader<>();
//        reader.setDelegate(flatFileReader());
//        reader.setResources(fileResources);
//        return reader;
//    }
//
//
//    @Bean
//    @StepScope
//    public FlatFileItemReader<City> flatFileReader() {
//        FlatFileItemReader<City> reader = new FlatFileItemReader<>();
//        reader.setResource(new ClassPathResource("data.txt"));
//
//        // 解析数据
//        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
//        tokenizer.setNames("id", "name", "countryCode", "district", "population");
//        // 解析后的数据映射为对象
//        DefaultLineMapper<City> mapper = new DefaultLineMapper<>();
//        mapper.setLineTokenizer(tokenizer);
//
//        mapper.setFieldSetMapper(new FieldSetMapper<City>() {
//            @Override
//            public City mapFieldSet(FieldSet fieldSet) throws BindException {
//                City city = new City();
//                city.setCountryCode(fieldSet.readString("countryCode"));
//                city.setDistrict(fieldSet.readString("district"));
//                city.setId(fieldSet.readInt("id"));
//                city.setName(fieldSet.readString("name"));
//                city.setPopulation(fieldSet.readLong("population"));
//                return city;
//            }
//        });
//        // 数据校验
//        mapper.afterPropertiesSet();
//        // 绑定映射
//        reader.setLineMapper(mapper);
//        return reader;
//    }
//
//
//
//}
