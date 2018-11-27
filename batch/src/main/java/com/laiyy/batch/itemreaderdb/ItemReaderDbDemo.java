//package com.laiyy.batch.itemreaderdb;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
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
// * @date 2018/11/16 10:01
// * @description
// */
//@Configuration
//public class ItemReaderDbDemo {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    private final DataSource dataSource;
//
//    private final UserItemWriter userItemWriter;
//
//    @Autowired
//    public ItemReaderDbDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, DataSource dataSource, UserItemWriter userItemWriter) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//        this.dataSource = dataSource;
//        this.userItemWriter = userItemWriter;
//    }
//
//    @Bean
//    public Job itemReaderDbJob(){
//        return jobBuilderFactory.get("itemReaderDbJob")
//                .start(itemReaderDbStep())
//                .build();
//    }
//
//    @Bean
//    public Step itemReaderDbStep() {
//        return stepBuilderFactory.get("itemReaderDbStep")
//                .<User, User>chunk(2)
//                .reader(userItemReader())
//                .writer(userItemWriter)
//                .build();
//    }
//
//    /**
//     * 注解 @StepScope 表示，该数据只在 Step 执行
//     * JdbcPagingItemReader 从 jdbc 中，使用分页获取数据
//     */
//    @Bean
//    @StepScope
//    public JdbcPagingItemReader<User> userItemReader(){
//        JdbcPagingItemReader<User> reader = new JdbcPagingItemReader<>();
//        reader.setDataSource(dataSource);
//        reader.setFetchSize(2);
//        // 把读取到的记录转换为 User 对象
//        reader.setRowMapper(new RowMapper<User>() {
//            @Override
//            public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
//                User user = new User();
//                user.setId(resultSet.getInt("id"));
//                user.setUsername(resultSet.getString("username"));
//                user.setPassword(resultSet.getString("password"));
//                user.setAge(resultSet.getInt("age"));
//                return user;
//            }
//        });
//        // 指定 SQL 语句
//        MySqlPagingQueryProvider provider = new MySqlPagingQueryProvider();
//        // 查询哪些字段
//        provider.setSelectClause("id, username, password, age");
//        // 查询哪个表
//        provider.setFromClause("from t_user");
//        // 根据那个字段进行排序
//        Map<String, Order> sortMap = new HashMap<>(2);
//        sortMap.put("id", Order.ASCENDING);
//        sortMap.put("username", Order.DESCENDING);
//        provider.setSortKeys(sortMap);
//
//        reader.setQueryProvider(provider);
//        return reader;
//    }
//
//}
