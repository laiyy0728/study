package com.laiyy.test.rabbitmq.helloworld;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by laiyy
 * Date 2017/9/30.
 */
public class Recv {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.154.129");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        System.out.println(" [*] Waiting for Messages . To exit press CTRL + F2");

        BasicProperties properties = new AMQP.BasicProperties.Builder().build();

//        DefaultConsumer consumer = new DefaultConsumer(channel);


    }

}
