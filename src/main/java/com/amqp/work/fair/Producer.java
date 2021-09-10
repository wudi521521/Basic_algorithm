package com.amqp.work.fair;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Dillon Wu
 * @Description: work模式--轮询模式
 * @date 2021/9/10 10:13
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
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
        connection=connectionFactory.newConnection("生产者");
        //3:通过连接获取通道channel
        Channel channel = null;
        channel = connection.createChannel();
        //创建一个Exchange，设置名称为exchange.3和自动删除为false
        //TODO 声明交换器,WORK模式没有类型,使用默认交换器
        //String exchange = "work_exchange";
        //channel.exchangeDeclare(exchange, "", false, false, null);
        //4:通过创建交换机，声明队列，绑定关系，路由Key，发送信息，和接收信息
        //param1===队列名称，
        //param2==== 是否要持久化,所谓持久化消息是否存盘，如果false非持久化，true是持久化？非持久化会存盘吗
        //param3==== 排他性（是否独占独立）
        //param4=== 是否会自动删除，随着最后一个消费者消息完毕消息以后是否把队列自动删除
        //param5=== 携带附属参数
        //TODO 声明队列
        channel.queueDeclare("work_queue",false,false,true,null);
        //5:准备消息内容
        for (int i = 0; i <20 ; i++) {
            String message=" message hello world "+"【"+i+"】";
            channel.basicPublish("","work_queue",null,message.getBytes());
            System.out.println("消息发送成功");
        }

        //7:关闭通道
        if (channel !=null && channel.isOpen()){
            channel.close();
        }
        //8:关闭连接
        connection.close();
    }
}
