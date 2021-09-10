package com.amqp.springboot.send;

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
}
