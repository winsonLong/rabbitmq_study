package direct;

import com.rabbitmq.client.*;
import utils.RabbitmqUtils;

import java.io.IOException;


public class Consumer1 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "logs_direct";

        //通道声明交换机以及交换机的类型
        channel.exchangeDeclare(exchangeName, "direct");

        //创建一个临时队列
        String queueName = channel.queueDeclare().getQueue();
        //基于routeKey绑定队列和交换机
        channel.queueBind(queueName, exchangeName, "error");

        //获取消费的消息
        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1：" + new String(body));
            }
        });
    }
}
