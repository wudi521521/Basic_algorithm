package com.config.amqp;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dillon Wu
 * @Description: 自定义AMQP绑定
 * @date 2021/9/10 11:37
 */
@Configuration
public class DirectMqConfiguration {
    //1:声明注册direct模式的交换机
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("direct_order_exchange",true,false);
    }
    //2:声明队列sms.fanout.queue email.fanout.queue duanxin.fanout.queue
    @Bean
    public Queue smsDirectQueue(){
        return new Queue("sms.direct.queue",true);
    }

    @Bean
    public Queue emailDirectQueue(){
        return new Queue("email.direct.queue",true);
    }

    @Bean
    public Queue duanxinDirectQueue(){
        return new Queue("duanxin.direct.queue",true);
    }

    //3:完成绑定关系(队列绑定到交换机上)
    @Bean
    public Binding smsDirectBinding(){
        return BindingBuilder.bind(smsDirectQueue()).to(directExchange()).with("direct_sms_routing");
    }

    @Bean
    public Binding emailDirectBinding(){
        return BindingBuilder.bind(emailDirectQueue()).to(directExchange()).with("direct_email_routing");
    }

    @Bean
    public Binding duanxinDirectBinding(){
        return BindingBuilder.bind(duanxinDirectQueue()).to(directExchange ()).with("direct_duanxin_routing");
    }
}
