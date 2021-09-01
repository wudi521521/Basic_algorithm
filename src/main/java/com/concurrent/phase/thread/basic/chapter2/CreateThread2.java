package com.concurrent.phase.thread.basic.chapter2;

import java.util.Arrays;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/18 11:03
 */
public class CreateThread2 {

    public static void main(String[] args) {

        Thread t = new Thread("thread-test"){
            @Override
            public void run() {
                try{
                    Thread.sleep(3000);

                }catch (Exception e){}
            }
        };
        t.start();
        t.getThreadGroup();
        System.out.println(t.getThreadGroup());
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getThreadGroup());
        System.out.println(Thread.currentThread().getThreadGroup().activeCount());
        Thread[] threads = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
        Thread.currentThread().getThreadGroup().enumerate(threads);
        Arrays.asList(threads).forEach(System.out::println);
    }
}
