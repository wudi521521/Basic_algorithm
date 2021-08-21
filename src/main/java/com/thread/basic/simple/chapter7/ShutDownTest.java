package com.thread.basic.simple.chapter7;

/**
 * @author Dillon Wu
 * @Description: java中添加hook钩子
 * @date 2021/8/20 10:37
 */
public class ShutDownTest {
    public static void main(String[] args) {
        Thread shut = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "=====shut");
        });
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "=====ing");
        }).start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "=====ing");
        }).start();

        Runtime.getRuntime().addShutdownHook(shut);
        System.out.println("==============");

    }
}
