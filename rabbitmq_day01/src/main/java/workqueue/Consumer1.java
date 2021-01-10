package workqueue;


import com.rabbitmq.client.*;
import utils.RabbitmqUtils;

import java.io.IOException;

public class Consumer1 {
    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitmqUtils.getConnection();
        final Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.queueDeclare("work", true, false, false, null);
        //参数1：队列名称 参数2：消息自动确认 true 消费者自动向rabbitmq确认消费 false 不会自动确认消息
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者2-" + new String(body));
                //参数1：确认队列中的哪个具体消息 参数2：是否开启多个消息同时确认
                //手动确认的目的是为了，消费者处理完后，通知队列把消息删除
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
