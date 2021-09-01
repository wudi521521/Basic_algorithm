package com.concurrent.phase.thread.reentrant;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/30 15:09
 */
public class ReentrantExample {
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        /*IntStream.rangeClosed(0, 2).forEach(item -> new Thread() {
            @Override
            public void run() {
                needLock();
            }
        }.start());*/
        Thread thread1 = new Thread(() -> testUnInterruptibly());
        thread1.start();
        TimeUnit.SECONDS.sleep(1);
        Thread thread2 = new Thread(()->testUnInterruptibly());
        thread2.start();
        TimeUnit.SECONDS.sleep(1);
        //thread1.interrupt();
        System.out.println("=================");

    }

    public static void testUnInterruptibly(){
        try {

            try {
                //TODO 抢不到锁就被打断
                //lock.lockInterruptibly();
                //TODO 试着去拿锁
                boolean result = ReentrantExample.lock.tryLock();
                if (result){

                }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Optional.of("The Thread-" + Thread.currentThread().getName() + " get lock and will do working").ifPresent(System.out::println);
            while (true){
            }
        }finally {
            lock.unlock();
        }
    }

    public static void needLock() {
        try {
            lock.lock();
            try {
                Optional.of("The Thread-" + Thread.currentThread().getName() + " get lock and will do working").ifPresent(System.out::println);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 比显示锁lock功能一样，性能稍微差一点
     */
    public static void needLockBySync() {
        synchronized (ReentrantExample.class) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
