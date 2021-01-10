package topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitmqUtils;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();
        //申明交换机以及交换机的类型 topic
        channel.exchangeDeclare("topics", "topic");

        //发布消息
        String routeKey = "user.save.tttt";
        channel.basicPublish("topics", routeKey, null, ("这里时topix动态路由模型,routekey:{" + routeKey + "}").getBytes());

        //释放资源
        RabbitmqUtils.closeConnectAndChannel(channel, connection);
    }
}
