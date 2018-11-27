package com.laiyy.batch.writer.itemwriterdb;

import com.laiyy.batch.itemreadermultifile.City;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author laiyy
 * @date 2018/11/26 13:55
 * @description
 */
//@Configuration
public class ItemWriterDbConfig {

    @Autowired
    private DataSource dataSource;


    @Bean
    public JdbcBatchItemWriter<City> flatFileItemWriter(){
        JdbcBatchItemWriter<City> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setSql("INSERT INTO t_city( id, name, countryCode, district, population) VALUES (:id, :name, :countryCode, :district, :population)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        return writer;
    }


}
