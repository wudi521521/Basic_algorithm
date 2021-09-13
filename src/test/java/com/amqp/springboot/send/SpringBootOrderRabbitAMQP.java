package com.amqp.springboot.send;

import com.amqp.springboot.send.OrderService;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/10 15:32
 */

     //Junit4
//指定为Spring环境中的单元测试
@RunWith(SpringRunner.class)
//指定为SpringBoot环境的单元测试，Application为启动@SpringBootTest
@SpringBootTest(classes = SpringBootOrderRabbitAMQP.class)
@ComponentScan("com.amqp.springboot.send")
public class SpringBootOrderRabbitAMQP {

    @Autowired
    public OrderService orderService;


    @Test
   public void contextLoad(){
        orderService.makeOder(1);
    }
}
