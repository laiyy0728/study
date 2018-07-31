package com.laiyy.kafka.producer.demo3;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author laiyy
 * @date 2018/7/31 11:52
 * @description
 */
public class AvroTest {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.67.128:9092");
        properties.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        properties.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        properties.put("schema.registry.url", "http://192.168.67.128:8081");

        // 创建一个 schema
        String schemaString = "{" +
                "    \"namespace\":\"customerManagement.avro\"," +
                "    \"type\":\"record\"," +
                "    \"name\":\"Customer\"," +
                "    \"fields\": [" +
                "        {" +
                "            \"name\":\"id\"," +
                "            \"type\":\"int\"" +
                "        }," +
                "        {" +
                "            \"name\":\"name\"," +
                "            \"type\":\"string\"" +
                "        }" +
                "    ]" +
                "}";

        // 注意，这里改变了 Producer 的泛型
        KafkaProducer<Integer, GenericRecord> producer = new KafkaProducer<>(properties);

        // 创建 schema parser
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(schemaString);

        // 根据 schema 创建数据
        GenericData.Record customer = new GenericData.Record(schema);
        customer.put("id", 2);
        customer.put("name", "zhangsan");

        // 创建生产者，注意与 KafkaProducer 的泛型相同
        ProducerRecord<Integer, GenericRecord> record = new ProducerRecord<>("test1", 2, customer);
        producer.send(record);


//        Customer customer = new Customer(1, "test");
//        System.out.println("准备发送的 customer：" + customer);
//        ProducerRecord<Integer, Customer> record = new ProducerRecord<>(topic, customer.getCustomerID(), customer);
//        producer.send(record);
    }

}
