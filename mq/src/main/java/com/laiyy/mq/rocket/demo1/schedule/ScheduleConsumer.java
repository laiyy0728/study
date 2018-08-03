package com.laiyy.mq.rocket.demo1.schedule;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author laiyy
 * @date 2018/8/3 16:52
 * @description
 * 消费者
 */
public class ScheduleConsumer {

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
                for (MessageExt message : msgs) {
                    System.err.println("Receive message [msgId=" + message.getMsgId() + "] " + (System.currentTimeMillis() - message.getStoreTimestamp()) + "ms later");
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();

        System.out.println("消费者已启动");
    }

}
