package com;

import com.amqp.springboot.send.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/8 10:07
 */
@Controller
@SpringBootApplication
public class BootStart {

    @Autowired
    private OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(BootStart.class, args);
    }

    @GetMapping("/1")
    public void order(){
        orderService.makeOder(1);
    }

    @GetMapping("/2")
    public void user(){
        orderService.userOder(2);
    }

}
