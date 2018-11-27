package com.laiyy.batch.writer.itemwriterdb;

import com.laiyy.batch.itemreadermultifile.City;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @author laiyy
 * @date 2018/11/26 13:50
 * @description
 */
//@Configuration
public class ItemReaderDbConfig {


    @Bean
    @StepScope
    public FlatFileItemReader<City> flatFileItemReader() {
        FlatFileItemReader<City> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("file1.txt"));

        // 解析数据
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("id", "name", "countryCode", "district", "population");
        // 解析后的数据映射为对象
        DefaultLineMapper<City> mapper = new DefaultLineMapper<>();
        mapper.setLineTokenizer(tokenizer);

        mapper.setFieldSetMapper(new FieldSetMapper<City>() {
            @Override
            public City mapFieldSet(FieldSet fieldSet) throws BindException {
                City city = new City();
                city.setCountryCode(fieldSet.readString("countryCode"));
                city.setDistrict(fieldSet.readString("district"));
                city.setId(fieldSet.readInt("id"));
                city.setName(fieldSet.readString("name"));
                city.setPopulation(fieldSet.readLong("population"));
                return city;
            }
        });
        // 数据校验
        mapper.afterPropertiesSet();
        // 绑定映射
        reader.setLineMapper(mapper);
        return reader;
    }


}
