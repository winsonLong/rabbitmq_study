package direct;


import com.rabbitmq.client.*;
import utils.RabbitmqUtils;

import java.io.IOException;

public class Customer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "logs_direct";

        //声明交换机 以及交换机类型
        channel.exchangeDeclare(exchangeName, "direct");
        //创建一个临时队列
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, exchangeName, "info");
        channel.queueBind(queueName, exchangeName, "error");
        channel.queueBind(queueName, exchangeName, "warning");
        //消费消息
        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2：" + new String(body));
            }
        });

    }
}
