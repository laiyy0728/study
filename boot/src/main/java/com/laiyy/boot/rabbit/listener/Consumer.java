package com.laiyy.boot.rabbit.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author laiyy
 * @date 2018/8/30 14:02
 * @description
 */
@Component
@RabbitListener(queues = "test_mq")
@Slf4j
public class Consumer {

    /**
     * 消息处理
     * @param message 接收到的消息
     */
    @RabbitHandler
    public void process(String message) {
        log.info(">>>>>>>>>>>>接收到的消息：{} <<<<<<<<", message);
    }

}
