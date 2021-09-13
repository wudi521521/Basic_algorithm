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
 * @Description: ttl-队列设置有效时间
 * @date 2021/9/10 21:41
 */
@Configuration
public class TTLRabbitMqConfiguration {

    /**
     * 声明交换器
     * @return
     */
    @Bean
    public DirectExchange ttlDirectExchange(){
        return new DirectExchange("ttl_direct_exchange",true,false);
    }

    /**
     * 声明队列
     * @return
     */
    @Bean
    public Queue directttlQueue(){
        //设置过期时间
        Map<String,Object> args = new HashMap<>();
        //设置过期时间(x-message-ttl固定写法)
        args.put("x-message-ttl",5000);
        //绑定死信队列(当队列中的信息过期后会进入死心队列中)
        args.put("x-dead-letter-exchange","dead_direct_exchange");
        args.put("x-dead-letter-routing-key","dead");//fanout不需要设置
        return new Queue("ttl.direct.queue",true,false,false,args);
    }

    /**
     * 绑定
     * @return
     */
    @Bean
    public Binding directttlBinding(){
        return BindingBuilder.bind(directttlQueue()).to(ttlDirectExchange()).with("ttl");
    }
}
