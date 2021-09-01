package com.concurrent.phase.thread.basic.chapter5;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/19 10:13
 */
public class SychronizedStaticTest {
    public static void main(String[] args) {
        Thread t1 = new Thread("T1") {
            @Override
            public void run() {
                SynchronizedStatic.m1();
            }
        };

      Thread t2=  new Thread("T2"){
            @Override
            public void run() {
                SynchronizedStatic.m2();
            }
        };
        t1.start();
        t2.start();
    }
}
