package com.laiyy.boot.rabbit.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laiyy
 * @date 2018/8/30 14:08
 * @description
 */
//@RestController
public class RabbitController {
    /**
     * 注入队列模版
     *
     * AmqpTemplate 定义了发送、接收消息的基本操作。
     */
    @Autowired
    private AmqpTemplate template;
    @GetMapping(value = "send")
    public String send(String msg) {
        template.convertAndSend(msg);
        System.out.println(template.receiveAndConvert("test_mq"));
        return "已发送消息：" + msg;
    }
}
