package com.amqp.topics;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Dillon Wu
 * @Description: topic 模式监听
 * @date 2021/9/10 21:16
 */
@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "duanxin.topic.queue",durable = "true",autoDelete = "false"),
        exchange = @Exchange(value = "topic_order_exchange",type = ExchangeTypes.TOPIC),
        key = "*.duanxin.#"
))
public class TopicDuanxinConsumer {

    public void receiveMessage(Integer message){
        System.out.println("TopicDuanxinConsumer--> "+message);
    }
}
