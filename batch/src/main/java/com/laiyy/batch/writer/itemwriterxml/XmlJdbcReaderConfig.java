//package com.laiyy.batch.writer.itemwriterxml;
//
//import com.laiyy.batch.itemreadermultifile.City;
//import org.springframework.batch.item.database.JdbcPagingItemReader;
//import org.springframework.batch.item.database.Order;
//import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.RowMapper;
//
//import javax.sql.DataSource;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author laiyy
// * @date 2018/11/26 14:31
// * @description
// */
////@Configuration
//public class XmlJdbcReaderConfig {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    public JdbcPagingItemReader<City> cityItemReader(){
//        JdbcPagingItemReader<City> reader = new JdbcPagingItemReader<>();
//        reader.setDataSource(dataSource);
//        reader.setFetchSize(2);
//        // 把读取到的记录转换为 User 对象
//        reader.setRowMapper(new RowMapper<City>() {
//            @Override
//            public City mapRow(ResultSet resultSet, int rowNum) throws SQLException {
//                City city = new City();
//                city.setId(resultSet.getInt("id"));
//                city.setPopulation(resultSet.getLong("population"));
//                city.setName(resultSet.getString("name"));
//                city.setDistrict(resultSet.getString("district"));
//                city.setCountryCode(resultSet.getString("countryCode"));
//                return city;
//            }
//        });
//        // 指定 SQL 语句
//        MySqlPagingQueryProvider provider = new MySqlPagingQueryProvider();
//        // 查询哪些字段
//        provider.setSelectClause("id, name, district, countryCode, population");
//        // 查询哪个表
//        provider.setFromClause("from t_city");
//        // 根据那个字段进行排序
//        Map<String, Order> sortMap = new HashMap<>(2);
//        sortMap.put("id", Order.ASCENDING);
//        sortMap.put("name", Order.DESCENDING);
//        provider.setSortKeys(sortMap);
//
//        reader.setQueryProvider(provider);
//        return reader;
//    }
//
//}
