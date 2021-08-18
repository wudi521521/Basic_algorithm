package com.thread.basic.simple.chapter3;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/18 13:41
 */
public class DaemonThread {
    public static void main(String[] args) throws InterruptedException{
        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+"running");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+"done");
                }catch (Exception e){

                }

            }
        };//1:new
        //runnable->running|dead|->blocked
        t.setDaemon(true);//守护线程
        t.start();
        Thread.sleep(5_000);
        System.out.println(Thread.currentThread().getName());
    }
}
