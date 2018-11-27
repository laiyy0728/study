//package com.laiyy.batch.listener;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.support.ListItemReader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @author laiyy
// * @date 2018/11/15 17:30
// * @description
// *
// * 执行结果
// * Job 执行之前，Job 名称：listenerJob
// * Step 执行之前，Step 名称：listenerStep1
// * （Chunk 设置为2，所以一次拿 2 个数据）
// * Java
// * Python
// * Step 执行之后，Step 名称：listenerStep1
// * Step 执行之前，Step 名称：listenerStep1
// * （两条数据取出来后，step 执行结束，开始获取下一个两条信息）
// * Swift
// * MyBatis
// * Step 执行之后，Step 名称：listenerStep1
// * Step 执行之前，Step 名称：listenerStep1
// * （数据全部取出执行结束）
// * Step 执行之后，Step 名称：listenerStep1
// * Job 执行之后，Job 名称：listenerJob
// *
// */
//@Configuration
//public class ListenerDemo {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//
//    @Autowired
//    public ListenerDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    @Bean
//    public Job listenerJob(){
//        return jobBuilderFactory.get("listenerJob")
//                .start(listenerStep1())
//                .listener(new MyJobListener())
//                .build();
//    }
//
//    @Bean
//    public Step listenerStep1() {
//        return stepBuilderFactory.get("listenerStep1")
//                // 以 Chunk 方式设置为每读取 2 个数据做一次相应的处理
//                // read、process、write；<String, String> 读取为 String，输出为 String
//                .<String, String>chunk(2)
//                // 容错
//                .faultTolerant()
//                // 设置 Chunk 监听
//                .listener(new MyChunkListener())
//                // 读取数据
//                .reader(itemReader())
//                // 输出数据
//                .writer(itemWriter())
//                .build();
//
//    }
//
//    @Bean
//    public ItemWriter<String> itemWriter(){
//        return new ItemWriter<String>() {
//            @Override
//            public void write(List<? extends String> items) throws Exception {
//                items.forEach(System.err::println);
//            }
//        };
//    }
//
//    @Bean
//    public ItemReader<String> itemReader(){
//        return new ListItemReader<>(Arrays.asList("Java", "Python", "Swift", "MyBatis"));
//    }
//
//
//}
