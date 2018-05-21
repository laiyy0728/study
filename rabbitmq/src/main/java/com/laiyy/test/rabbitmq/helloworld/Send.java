package com.laiyy.test.rabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by laiyy
 * Date 2017/9/30.
 */
public class Send {

    private final static  String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        // 抽象一个 Socket 链接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.154.129");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel(); // 创建管道，大多数操作都在管道上
        // 声明一个发生队列
        channel.queueDeclare(QUEUE_NAME, false, false,  false, null);
        String messagge = "Hello World";
        channel.basicPublish("", QUEUE_NAME, null, messagge.getBytes());
        System.out.println("[X] Sent " + messagge);
        channel.close();
        connection.close();
    }

}
