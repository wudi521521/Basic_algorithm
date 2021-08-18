package com.thread.basic.simple.chapter3;

import java.util.Optional;

/**
 * @author Dillon Wu
 * @Description: 线程简单API
 * @date 2021/8/18 14:12
 */
public class SimpleThreadAPI {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            Optional.of("Hello").ifPresent(System.out::println);
            try {
                Thread.sleep(1_000);
            }catch (Exception e){
                e.printStackTrace();
            }
        },"thread01");
        Optional.of(t.getName()).ifPresent(System.out::println);
        Optional.of(t.getId()).ifPresent(System.out::println);
        Optional.of(t.getPriority()).ifPresent(System.out::println);
    }
}
