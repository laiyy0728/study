package com.laiyy.mq.rocket.demo1.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author laiyy
 * @date 2018/8/3 14:32
 * @description
 * 可靠异步生产者
 */
public class AsyncProducer {

    public static void main(String[] args) throws Exception{

        // 创建生产者，组名：test
        DefaultMQProducer producer = new DefaultMQProducer("test");

        // 设置生产者 name server
        producer.setNamesrvAddr("192.168.67.129:9876");


        // 启动生产者
        producer.start();

        // 设置异步发送失败的话，RocketMQ 重试次数，当前为不重试
        producer.setRetryTimesWhenSendAsyncFailed(1);

        for (int index = 0; index < 100; index++) {
            final int number = index++;
            // 封装消息，设置消息的 key
            Message message = new Message("TestTopic", "TagA", "Hello World".getBytes(RemotingHelper.DEFAULT_CHARSET));

            // 异步发送消息
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    // 成功回调
                    System.out.println("发送第 " + number +"条信息成功，MsgId：" + sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable throwable) {
                    // 失败回调
                    System.out.println("发送第 " + number +"条信息失败，失败信息：" + throwable.getLocalizedMessage());
                }
            }, 3000);
        }

        // 线程休眠 10 秒，防止 Producer 提前关闭导致回调失败
        Thread.sleep(1000 * 10);

        // 关闭生产者
        producer.shutdown();


    }

}
