package com.concurrent.phase.thread.basic.chapter2;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/18 10:47
 */
public class CreateThread {
    public static void main(String[] args) {
        Thread t1 = new Thread();
        Thread t2 = new Thread(){

        };
        t1.start();
        t2.start();
        System.out.println(t1.getName());
        System.out.println(t2.getName());

        Thread t3 = new Thread("MyName");
        //函数式接口写法
        Thread t4 = new Thread(()->
                System.out.println("Runnable**********"+Thread.currentThread().getName()),"RunnableThread");
        System.out.println(t3.getName());
        t4.start();
        System.out.println(t4.getName());
    }
}
