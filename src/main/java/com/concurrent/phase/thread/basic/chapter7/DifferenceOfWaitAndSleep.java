package com.concurrent.phase.thread.basic.chapter7;

import java.util.stream.Stream;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/19 14:57
 */
public class DifferenceOfWaitAndSleep {

    private final static  Object LOCK = new Object();
    public static void main(String[] args) {
        Stream.of("T1","T2").forEach(name->{
            new Thread(name){
                @Override
                public void run() {
                    m2();
                }
            }.start();
        });
    }

    public static void m1(){
        synchronized (LOCK){
            try{
                System.out.println("The thread"+Thread.currentThread().getName());
                Thread.sleep(1_000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }

    public static void m2(){
        synchronized (LOCK){
            try{
                System.out.println("The thread"+Thread.currentThread().getName());
                //需要notify()或者notifyAll()唤醒，如果不唤醒一直阻塞到此
                LOCK.wait();
                System.out.println("============"+Thread.currentThread().getName());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }
}
