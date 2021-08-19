package com.thread.basic.simple.chapter5;

/**
 * @author Dillon Wu
 * @Description: this 锁
 * @date 2021/8/18 21:55
 */
public class SynchronizedThis {
    public static void main(String[] args) {

        ThisLock thisLock = new ThisLock();
        Thread t = new Thread(() -> {
            thisLock.ml();
        },"T1");

        Thread t2 = new Thread(() -> {
            thisLock.ml();
        },"T2");
        t.start();
        t2.start();
    }

    static class ThisLock {
        public synchronized void ml() {
            try {
                Thread.sleep(5_000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public synchronized void ml2() {
            try {
                Thread.sleep(5_000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
