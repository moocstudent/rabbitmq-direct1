package com.ykmimi.rabbitmqdirect.customer;

import cn.hutool.core.util.RandomUtil;
import com.rabbitmq.client.*;
import com.ykmimi.rabbitmqdirect.util.RabbitMQUtil;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class TestDirectCustomer {

    private final static String QUEUE_NAME = "direct_queue";

    public static void startCustomer() throws IOException, TimeoutException {
        //为当前消费者取随机名
        final String name = "consumer-"+RandomUtil.randomString(5);

        //判断服务器是否启动
        RabbitMQUtil.checkServer();
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ地址
        factory.setHost("localhost");
        //创建一个新的连接
        Connection connection = factory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        //声明要关注的队列
        channel.queueDeclare(QUEUE_NAME,false,false,true,null);
        System.out.println(name+"等待接受消息");
        //DefaultConsumer类实现了Consumer接口,通过传入一个频道,
        //告诉服务器我们需要哪个频道的消息,如果频道中有消息,就会执行回调函数handleDelivery
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(name + " 接收到消息 '" + message + "'");
            }
        };
        //自动回复队列应答 -- RabbitMQ中的消息确认机制
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
