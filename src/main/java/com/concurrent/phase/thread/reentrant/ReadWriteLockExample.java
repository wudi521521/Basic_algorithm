package com.concurrent.phase.thread.reentrant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/30 16:32
 */
public class ReadWriteLockExample {

    /**
     * wwx
     * wrx
     *
     */
    private final static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    //读锁
    private final static Lock readLock = readWriteLock.readLock();
    //写锁
    private final static Lock writeLock = readWriteLock.writeLock();

    private final static List<Long> data = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> write());
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        Thread thread2 = new Thread(() -> read());
        thread2.start();

    }

    public static void write() {
        try {
            writeLock.lock();
            data.add(System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            writeLock.unlock();
        }
    }

    public static void read() {
        try {
            readLock.lock();
            data.forEach(System.out::println);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"====================");
        } finally {
            readLock.unlock();
        }
    }

}
