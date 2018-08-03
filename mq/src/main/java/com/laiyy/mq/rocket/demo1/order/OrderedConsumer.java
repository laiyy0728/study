package com.laiyy.mq.rocket.demo1.order;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author laiyy
 * @date 2018/8/3 16:16
 * @description
 */
public class OrderedConsumer {

    public static void main(String[] args) throws Exception{
        // 创建一个消费者，消费者组：test
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test");

        // 设置 name server 地址
        consumer.setNamesrvAddr("192.168.67.129:9876");

        // 设置消费者从从开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        // 设置消费者订阅 topic、tag
        consumer.subscribe("TestTopic", "TagA || TagC || TagD");

        // 注册消费者消息监听器
        consumer.registerMessageListener(new MessageListenerOrderly() {

            // 保证 long 类型的原子性。 在 32 位心疼中，64 位的 long、double 会被 JVM 当做两个分离的 32 位操作
            AtomicLong consumeTimes = new AtomicLong(0);

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                // 设置不字段提交
                context.setAutoCommit(false);
                System.out.println(Thread.currentThread().getName() + " Receive New Message : " + msgs);

                this.consumeTimes.incrementAndGet();
                // 根据不同的值，返回不同的消费状态
                if ( this.consumeTimes.get() % 2 == 0 ){
                    return ConsumeOrderlyStatus.SUCCESS;
                } else if (this.consumeTimes.get() % 3 == 0) {
                    return ConsumeOrderlyStatus.ROLLBACK;
                } else if (this.consumeTimes.get() % 4 == 0){
                    return ConsumeOrderlyStatus.COMMIT;
                } else if (this.consumeTimes.get() % 5 == 0) {
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();

        System.out.println("Consumer started");

    }

}
