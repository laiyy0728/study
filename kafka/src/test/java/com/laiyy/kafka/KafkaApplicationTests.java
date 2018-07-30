//package com.laiyy.kafka;
//
//import org.apache.kafka.clients.producer.Callback;
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.apache.kafka.clients.producer.RecordMetadata;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Properties;
//import java.util.concurrent.Future;
//
////@RunWith(SpringRunner.class)
////@SpringBootTest
//public class KafkaApplicationTests {
//
//    @Test
//    public void contextLoads() {
//
//        // 1、创建一个 Properties 对象
//        Properties kafkaProperties = new Properties();
//        // 2、设置 kafka 地址
//        kafkaProperties.put("bootstrap.servers", "192.168.67.128:9092");
//        // 3、设置键值为字符串类型
//        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//
//        // 4、创建新的生产者，并为键和值设置了恰当的类型
//        KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProperties);
//
//        // 指定目标 topic，发送的键值。键值对象的类型必须与序列化器和生产者对象相匹配
//        ProducerRecord<String, String> record = new ProducerRecord<>("test","list_1", "message");
//
//        // 发送同步数据
////        try {
////            // 发送数据，会返回一个 future。
////            Future<RecordMetadata> future = producer.send(record);
////            // 打印返回的数据
////            System.out.println(future.get());
////        }catch (Exception e){
////            // 可能出现异常
////            e.printStackTrace();
////        }
//
//        // 发送异步数据
//        producer.send(record, new CustomerProducerCallback());
//
//    }
//
//    private class CustomerProducerCallback implements Callback {
//
//        @Override
//        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
//            System.out.println(recordMetadata.toString());
//            if (e != null) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//}
