package com.laiyy.mq.rocket.demo1.filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author laiyy
 * @date 2018/8/6 16:20
 * @description
 * 自定义消息过滤 消费者
 */
public class FilterConsumer {

    public static void main(String[] args) throws Exception{
        // 创建消费者，所属组：test
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test");

        // 设置 name server
        consumer.setNamesrvAddr("192.168.67.129:9876");

        // 订阅组，设置消息过滤规则
        consumer.subscribe("TestTopic", MessageSelector.bySql("number between 1 and 3"));

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
