package com.amqp.springboot.consume;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/10 17:18
 */
@Component
@RabbitListener(queues = {"duanxin.fanout.queue"})
public class FanoutDuanxinCondumer {

    @RabbitHandler
    public void receiveMessage(Integer message){
        System.out.println("******FanoutDuanxinCondumer******{}"+message);
    }
}
