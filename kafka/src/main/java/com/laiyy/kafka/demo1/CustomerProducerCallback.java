package com.laiyy.kafka.demo1;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * @author laiyy
 * @date 2018/7/30 17:15
 * @description
 */
public class CustomerProducerCallback implements Callback {

    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        System.out.println(recordMetadata.toString());
        if (e != null) {
            e.printStackTrace();
        }
    }
}
