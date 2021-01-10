package com.longson.rabbitmq_springboot.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component //默认创建队列时 持久化， 非独占 不是自动删除的
@RabbitListener(queuesToDeclare =@Queue(value="hello",durable="false",autoDelete = "true"))
public class HelloCustomer {

    @RabbitHandler
    public void receive(String message){
        System.out.println("message========"+message);
    }

}
