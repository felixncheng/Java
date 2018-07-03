package com.chengmboy.app.rabbitmq;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

/**
 * @author cheng_mboy
 */
@Component
public class Sender {

    private final AmqpTemplate rabbitTemplate;

    public Sender(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send() {
        String msg = "hello rabbitmq:"+new Date();
        System.out.println("Sender:"+msg);
        this.rabbitTemplate.convertAndSend("hello", msg);
    }
}
