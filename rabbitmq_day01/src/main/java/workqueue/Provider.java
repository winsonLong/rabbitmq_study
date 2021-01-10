package workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitmqUtils;

import java.io.IOException;


public class Provider {

    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitmqUtils.getConnection();
        //获取通道对象
        final Channel channel = connection.createChannel();
        //通道生命队列
        channel.queueDeclare("work", true, false, false, null);
        //发布消息
        for (int i = 0; i <= 20; i++) {
            channel.basicPublish("", "work", null, (i + "hello work queue").getBytes());
        }
        //关闭资源
        RabbitmqUtils.closeConnectAndChannel(channel, connection);
    }
}
