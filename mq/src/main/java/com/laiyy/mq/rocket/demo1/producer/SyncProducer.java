package com.laiyy.mq.rocket.demo1.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;


/**
 * @author laiyy
 * @date 2018/8/3 13:49
 * @description
 * 同步可靠生产者
 */
public class SyncProducer {

    public static void main(String[] args) throws Exception {

        // 创建一个生产者组，组名：test
        DefaultMQProducer producer = new DefaultMQProducer("test");

        // 设置 name server 地址
        producer.setNamesrvAddr("192.168.67.129:9876");

        // 启动生产者
        producer.start();

        for (int index = 0; index < 100; index++) {
            // 创建消息
            Message message = new Message("TestTopic", "TagA", ("Hello RocketMQ" + index).getBytes(RemotingHelper.DEFAULT_CHARSET));

            // 发送消息
            SendResult result = producer.send(message);
            System.err.println(result);
        }

        // 关闭生产者
        producer.shutdown();

    }

}
