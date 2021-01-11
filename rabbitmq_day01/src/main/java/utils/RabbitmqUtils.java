package utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

import java.util.concurrent.TimeoutException;

public class RabbitmqUtils {

    public static ConnectionFactory connectionFactory = new ConnectionFactory();

    static {
        //重量级资源
        connectionFactory.setHost("47.116.129.207");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
    }


    //定义提供创建连接对象的方法
    public static Connection getConnection() {

        try {

            return connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnectAndChannel(Channel channel, Connection conn) {
        try {
            if (channel != null) {
                channel.close();
            }
            if (conn != null) {
                conn.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
