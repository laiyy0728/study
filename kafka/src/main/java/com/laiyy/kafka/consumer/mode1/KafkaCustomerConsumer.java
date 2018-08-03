package com.laiyy.kafka.consumer.mode1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;

/**
 * @author laiyy
 * @date 2018/7/31 16:47
 * @description
 */
public class KafkaCustomerConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaCustomerConsumer.class);


    public static void main(String[] args) {
        // 1、创建一个 Properties 对象
        Properties kafkaProperties = new Properties();
        // 2、设置 kafka 地址
        kafkaProperties.put("bootstrap.servers", "192.168.67.128:9092");
        // 3、设置键值为字符串类型
        kafkaProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // 4、设置消费者组
        kafkaProperties.put("group.id", "test-consumer-group");

        // 5、创建一个消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(kafkaProperties);

        // 6、订阅主题
        consumer.subscribe(Collections.singletonList("test2"));

        // 7、消费者具体实现
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    LOGGER.info(">>>>>>>topic：{}，partition：{}，offset：{}，customer：{}， country：{} <<<<<<<<<",
                            record.topic(), record.partition(), record.offset(), record.key(), record.value());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }

    }


}
