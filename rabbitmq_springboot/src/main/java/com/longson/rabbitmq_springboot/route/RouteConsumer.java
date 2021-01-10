package com.longson.rabbitmq_springboot.route;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RouteConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue//创建临时队列
                    ,exchange = @Exchange(value="directs",type = "direct")//指定交换机和类型
                    ,key = {"info","error","debug"}
            )
    })
    public void receive1(String message){
        System.out.println("messgae1：==="+message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue//创建临时队列
                    ,exchange = @Exchange(value="directs",type = "direct")//指定交换机和类型
                    ,key = {"error"}
            )
    })
    public void receive2(String message){
        System.out.println("messgae2：==="+message);
    }
}
