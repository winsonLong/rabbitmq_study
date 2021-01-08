package helloword;

import com.rabbitmq.client.*;
import org.junit.Test;
import utils.RabbitmqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        /*//创建连接工厂
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("47.116.129.207");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");

        //创建连接对象
        Connection connection=connectionFactory.newConnection();*/

        //通过工具类获取连接对象
        Connection connection= RabbitmqUtils.getConnection();

        //创建通道
        Channel channel=connection.createChannel();

        //通道绑定对象
        channel.queueDeclare("hello",false,false,false,null);

        //消费消息
        /*
         * 参数1：消费哪个队列的消息 队列名称
         * 参数2：开始消息的自动确认机制
         * 参数3：消费时的回调接口
         * */
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            @Override//最后一个参数：消息队列中取出的消息
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String（body）"+new String(body));
            }
        });

        //不关闭是为了一直监听
        //channel.close();
        //connection.close();
    }

    @Test
    public void test() throws IOException, TimeoutException {

    }
}
