package com.laiyy.mq.rocket.demo1.broadcast;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author laiyy
 * @date 2018/8/3 16:42
 * @description
 *
 * 广播消息消费者
 */
public class BroadcastConsumer {

    public static void main(String[] args) throws Exception{
        // 创建消费者，所属组：test
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test");

        // 设置 name server
        consumer.setNamesrvAddr("192.168.67.129:9876");

        // 订阅组
        consumer.subscribe("TestTopic", "TagF");

        // 注册消息监听器
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.println(Thread.currentThread().getName() + " Receive new message: " + msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();

        System.out.println("消费者已启动");
    }

}
