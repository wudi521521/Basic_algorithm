package com.concurrent.phase.thread.basic.chapter1;

/**
 * @author Dillon Wu
 * @Description: 简单线程设计
 * @date 2021/8/17 17:16
 */
public class TryConcurrency {

    public static void main(String[] args) {
        Thread thread = new Thread("Thread_A"){
            @Override
            public void run() {
                for (int i=0;i<1000;i++){
                    System.out.println("Task->"+i+"__A");
                }
            }
        };

        thread.start();
        for (int a=0;a<1000;a++){
            System.out.println("Task->"+a+"__B");
        }
    }
}
