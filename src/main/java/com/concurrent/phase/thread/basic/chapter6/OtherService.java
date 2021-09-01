package com.concurrent.phase.thread.basic.chapter6;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/19 10:34
 */
public class OtherService {

    private final Object lock = new Object();

    private DeadLock deadLock;

    public void s1(){
        synchronized (lock){
            System.out.println("s1=============");
        }
    }

    public void s2(){
        synchronized (lock){
            System.out.println("s2=============");
            deadLock.m2();
        }
    }

    public void setDeadLock(DeadLock deadLock){
        this.deadLock=deadLock;
    }

}
