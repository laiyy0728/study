package com.laiyy.mq.rocket.demo1.batch;

import com.google.common.collect.Lists;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

/**
 * @author laiyy
 * @date 2018/8/6 14:02
 * @description
 */
public class SplitterProducer {

    public static void main(String[] args) throws Exception{
        // 创建一个生产者，组名：test
        DefaultMQProducer producer = new DefaultMQProducer("test");

        // 设置 name server
        producer.setNamesrvAddr("192.168.67.129:9876");

        // 启动生产者
        producer.start();

        String topic = "TestTopic";

        // 封装消息组
        List<Message> messages = Lists.newArrayList();
        messages.add(new Message(topic, "TagA", "OrderID001", "HelloWorld0".getBytes(RemotingHelper.DEFAULT_CHARSET)));
        messages.add(new Message(topic, "TagA", "OrderID002", "HelloWorld1".getBytes(RemotingHelper.DEFAULT_CHARSET)));
        messages.add(new Message(topic, "TagA", "OrderID003", "HelloWorld2".getBytes(RemotingHelper.DEFAULT_CHARSET)));

        // 使用 Splitter 分割消息
        ListSplitter splitter = new ListSplitter(messages);
        while (splitter.hasNext()) {
            List<Message> messageList = splitter.next();
            producer.send(messageList);
        }

        producer.shutdown();
    }

}
