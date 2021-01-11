package com.longson.rabbitmq_springboot.config;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    /**
     *此处的ConnectionFactory 如果还是用com.rabbitmq.client.ConnectionFactory;
     * 也没有毛病，一样可以配置相关属性。但是启动的时候就是报异常：
     *
     * Failed to check/redeclare auto-delete queue(s).
     */
    @Bean
    public CachingConnectionFactory  connectionFactory(){
        //定义连接工厂
        CachingConnectionFactory factory = new CachingConnectionFactory ();
        //设置服务地址
        factory.setHost("47.116.129.207");
        //端口
        factory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("/ems");
        factory.setUsername("ems");
        factory.setPassword("123");

        return factory;
    }
}