package com.laiyy.mq.rocket.demo1.filter;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author laiyy
 * @date 2018/8/6 16:18
 * @description
 *
 * 自定义过滤消息 生产者
 */
public class FilterProducer {

    public static void main(String[] args) throws Exception{

        // 创建一个生产者，组名：test
        DefaultMQProducer producer = new DefaultMQProducer("test");

        // 设置 name server
        producer.setNamesrvAddr("192.168.67.129:9876");

        // 启动生产者
        producer.start();

        String topic = "TestTopic";

        Message message = new Message(topic, "TagA", "OrderID001", "HelloWorld0".getBytes(RemotingHelper.DEFAULT_CHARSET));

        // 设置自定义配置，消费者根据自定义配置进行消息过滤
        message.putUserProperty("number", "3");

        SendResult result = producer.send(message);

        System.err.println(result);

        producer.shutdown();

    }

}
