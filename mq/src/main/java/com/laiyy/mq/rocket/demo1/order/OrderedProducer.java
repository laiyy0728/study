package com.laiyy.mq.rocket.demo1.order;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

/**
 * @author laiyy
 * @date 2018/8/3 15:41
 * @description
 */
public class OrderedProducer {

    public static void main(String[] args) throws Exception{
        // 创建生产者，生产者组：test
        DefaultMQProducer producer = new DefaultMQProducer("test");

        // 设置生产者 name server
         producer.setNamesrvAddr("192.168.67.129:9876");

         // 启动生产者
        producer.start();

        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};

        for (int index = 0; index < 100; index++) {
            // 获取 orderId
            int orderId = index % 10;

            // 封装消息
            Message message = new Message("TestTopic", tags[index % tags.length], "KEY_" + index, "Hello Order".getBytes(RemotingHelper.DEFAULT_CHARSET));

            // 选择队列
            SendResult result = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    // 计算当前生产者使用哪个队列
                    int id = (int) o;
                    int index = id % list.size();
                    return list.get(index);
                }
            }, orderId);

            // 消息生产结果
            System.err.println(result);

        }

        // 关闭生产者
        producer.shutdown();

    }

}
