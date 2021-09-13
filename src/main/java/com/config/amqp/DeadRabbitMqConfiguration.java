package com.config.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dillon Wu
 * @Description: 定义死心队列
 * @date 2021/9/10 22:34
 */
@Configuration
public class DeadRabbitMqConfiguration {

    @Bean
    public DirectExchange deadExchange(){
        return new DirectExchange("dead_direct_exchange",true,false);
    }

    @Bean
    public Queue deadQueue(){
        return new Queue("dead.direct.queue",true);
    }

    @Bean
    public Binding deadBinds(){

        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with("dead");
    }
}
