package com.thread.basic.simple.chapter6;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/19 10:42
 */
public class DeadLockTest {
    public static void main(String[] args) {
        OtherService otherService = new OtherService();
        DeadLock deadLock = new DeadLock(otherService);
        otherService.setDeadLock(deadLock);
        new Thread(){
            @Override
            public void run() {
                deadLock.m1();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                otherService.s2();
            }
        }.start();
    }
}
