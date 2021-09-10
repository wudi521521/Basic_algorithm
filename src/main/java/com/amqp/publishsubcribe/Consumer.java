package com.amqp.publishsubcribe;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/9 17:43
 */
public class Consumer {
    private static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //所有的中间件技术都是基于tcp/ip协议基础之上构建新型的协议规范，只不过rabbitMQ遵循的是AMQP
            //1:创建连接工程
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("39.107.245.189");
            connectionFactory.setPort(5672);
            connectionFactory.setUsername("eblocks_dev");
            connectionFactory.setPassword("eblocks2018@china.com");
            connectionFactory.setVirtualHost("/");
            //2:创建连接
            Connection connection = null;
            try {
                connection = connectionFactory.newConnection("消费者");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            //3:通过连接获取通道channel
            Channel channel = null;
            try {
                channel = connection.createChannel();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //TODO 定义队列的名称就是线程的名称
            String queueName = Thread.currentThread().getName();
            System.out.println("======queue====="+queueName);
            try {
                System.out.println("---------------------");
                //TODO 消费者消费
                String consume = channel.basicConsume(queueName, true, new DeliverCallback() {
                    public void handle(String consumerTag, Delivery delivery) throws IOException {
                        System.out.println("收到消息:" + new String(delivery.getBody(), "UTF-8"));
                    }
                    //出现异常打印
                }, new CancelCallback() {
                    public void handle(String consumerTag) throws IOException {
                        System.out.println("接收失败");
                    }
                });
                System.out.println("开始接收消息");
                System.in.read();
                //7:关闭通道
                if (channel != null && channel.isOpen()) {
                    channel.close();
                }
                //8:关闭连接
                connection.close();
            } catch (Exception e) {
                System.out.println("---");
            }
        }

    };

    public static void main(String[] args) {
        new Thread(runnable,"queue1").start();
        new Thread(runnable,"queue2").start();
    }

}
