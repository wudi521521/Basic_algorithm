package com.amqp.springboot.send;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/10 11:34
 */
@Component
@Service
public class OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void makeOder(Integer orderId){
        System.out.println("====================");
        String exchangeName = "fanout_order_exchange";
        String routingKey = "";
        //RabbitTemplate rabbitTemplate=new RabbitTemplate();
        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);
    }

    public void userOder(Integer orderId){
        System.out.println("====================");
        String exchangeName = "direct_order_exchange";

        String routingKeySMS = "direct_sms_routing";
        String routingKeyDuanxin = "direct_duanxin_routing";
        String routingKeyweixin = "direct_email_routing";
        //RabbitTemplate rabbitTemplate=new RabbitTemplate();
        rabbitTemplate.convertAndSend(exchangeName,routingKeySMS,orderId);
        rabbitTemplate.convertAndSend(exchangeName,routingKeyDuanxin,orderId);

    }

    public void topicOrder(Integer orderId){
        System.out.println("====================");
        String exchangeName = "topic_order_exchange";

        String routing="";
        rabbitTemplate.convertAndSend(exchangeName,routing,orderId);


    }

    public void ttl(Integer orderId){
        System.out.println("====================");
        String exchangeName = "ttl_direct_exchange";

        String routing="ttl";
        rabbitTemplate.convertAndSend(exchangeName,routing,orderId);


    }

    public void message(Integer orderId){
        System.out.println("====================");
        String exchangeName = "message_direct_exchange";
        String routing="ttl";
        //消息设置过期时间在发送的时候
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //这里设置字符串,5000毫秒
                message.getMessageProperties().setExpiration("5000");
                message.getMessageProperties().setContentEncoding("UTF-8");
                return message;
            }
        };
        rabbitTemplate.convertAndSend(exchangeName,routing,orderId,messagePostProcessor);


    }
}
