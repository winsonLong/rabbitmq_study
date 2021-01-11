package direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitmqUtils;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();

        //通过通道声明交换机 参数1：交换机名称 参数2：路由模式 direct
        channel.exchangeDeclare("logs_direct", "direct");

        //发送消息
        String routingKey = "error";
        channel.basicPublish("logs_direct", routingKey, null, ("这是direct模型发布的基于routeKey{" + routingKey + "}发送的消息").getBytes());

        RabbitmqUtils.closeConnectAndChannel(channel, connection);
    }
}
