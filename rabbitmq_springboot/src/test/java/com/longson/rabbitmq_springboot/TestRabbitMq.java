package com.longson.rabbitmq_springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = RabbitmqSpringbootApplication.class)
@RunWith(SpringRunner.class)
public class TestRabbitMq {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //route路由模式
    @Test
    public void testRoute(){
        rabbitTemplate.convertAndSend("directs","error","发送info的路由信息");
    }

    //fanout 广播
    @Test
    public void testFanout(){
        rabbitTemplate.convertAndSend("logs","","Fanout的模型发送的消息");
    }

    //WORK
    @Test
    public void testwork(){
        for(int i=0;i<10;i++)
        rabbitTemplate.convertAndSend("work","work模型");
    }

   /* @Test
    //hello word
    public void test(){
        rabbitTemplate.convertAndSend("hello","hello word");
    }*/








}
