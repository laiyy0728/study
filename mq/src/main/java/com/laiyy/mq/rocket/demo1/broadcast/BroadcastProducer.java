package com.laiyy.mq.rocket.demo1.broadcast;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author laiyy
 * @date 2018/8/3 16:39
 * @description
 * 广播方式生产者
 */
public class BroadcastProducer {

    public static void main(String[] args) throws Exception{
        // 创建一个生产者，组名：test
        DefaultMQProducer producer = new DefaultMQProducer("test");

        // 设置 name server
        producer.setNamesrvAddr("192.168.67.129:9876");

        // 启动生产者
        producer.start();

        for (int index = 0; index < 100; index++) {
            // 封装消息
            Message message = new Message("TestTopic", "TagF", "OrderId", "Hello World".getBytes(RemotingHelper.DEFAULT_CHARSET));

            // 发送消息
            SendResult result = producer.send(message);

            // 消息回执
            System.err.println(result);
        }

        producer.shutdown();

    }

}
