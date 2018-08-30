package com.laiyy.boot.rabbit.conf;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author laiyy
 * @date 2018/8/30 13:59
 * @description
 */
@Configuration
public class RabbitConfig {

    /**
     * 定义一个名为 test_mq 的队列
     * @return 队列实例
     */
    @Bean
    public Queue queue(){
        return new Queue("test_mq");
    }

}
