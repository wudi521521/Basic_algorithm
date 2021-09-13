package com.config.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dillon Wu
 * @Description: ttl-消息设置有效时间
 * @date 2021/9/10 21:41
 */
@Configuration
public class TTLMessageRabbitMqConfiguration {

    /**
     * 声明交换器
     * @return
     */
    @Bean
    public DirectExchange messageDirectExchange(){
        return new DirectExchange("message_direct_exchange",true,false);
    }

    /**
     * 声明队列
     * @return
     */
    @Bean
    public Queue directMessageQueue(){
        //设置过期时间
        Map<String,Object> args = new HashMap<>();
        //设置过期时间(x-message-ttl固定写法)
        args.put("x-message-ttl",5000);
        return new Queue("message.direct.queue",true,false,false,args);
    }

    /**
     * 绑定
     * @return
     */
    @Bean
    public Binding direcMessageBinding(){
        return BindingBuilder.bind(directMessageQueue()).to(messageDirectExchange()).with("ttl");
    }
}
