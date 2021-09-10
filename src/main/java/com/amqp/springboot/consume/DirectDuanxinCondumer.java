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
@RabbitListener(queues = {"duanxin.direct.queue"})
public class DirectDuanxinCondumer {

    @RabbitHandler
    public void receiveMessage(Integer message){
        System.out.println("******DirectDuanxinCondumer******{}"+message);
    }
}
