package com.laiyy.mq.rocket.demo1.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author laiyy
 * @date 2018/8/3 15:35
 * @description
 */
public class OnewayProducer {

    public static void main(String[] args) throws Exception{
        // 创建生产者，所属组名：test
        DefaultMQProducer producer = new DefaultMQProducer("test");

        // 设置 name server
        producer.setNamesrvAddr("192.168.67.129:9876");

        // 启动生产者
        producer.start();

        for (int index = 0; index < 100; index++) {
            // 封装消息
            Message message = new Message("TestTopic", "TagA", "OneWay".getBytes(RemotingHelper.DEFAULT_CHARSET));

            // 单向发送消息
            producer.sendOneway(message);
        }

        // 关闭生产者
        producer.shutdown();

    }

}
