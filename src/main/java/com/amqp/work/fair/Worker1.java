package com.amqp.work.fair;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/10 10:14
 */
public class Worker1 {
    private static Runnable runnable= new Runnable() {
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
            Connection connection =null;
            try {
                connection=connectionFactory.newConnection("消费者");
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
            Channel finalChannel = channel;
            //公平分发默认为0
            try {
                finalChannel.basicQos(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try{
                System.out.println("---------------------");
                String consume = finalChannel.basicConsume("work_queue", false, new DeliverCallback() {
                    public void handle(String consumerTag, Delivery delivery) throws IOException {
                        System.out.println("收到消息:" + new String(delivery.getBody(), "UTF-8"));
                        //手动应答
                        finalChannel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
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
                if (channel !=null && channel.isOpen()){
                    channel.close();
                }
                //8:关闭连接
                connection.close();
            }catch (Exception e){
                System.out.println("---");
            }
        }

    };
    public static void main(String[] args) throws IOException, TimeoutException {
        new Thread(runnable).start();
    }

}
