package com.concurrent.phase.thread.reentrant;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/30 16:47
 */
public class ConditionExample {


    private final static ReentrantLock lock = new ReentrantLock();

    private final static Condition condition = lock.newCondition();

    private static int data = 0;

    //没有被使用为true
    private static volatile boolean noUse = true;

    private static void buildData() throws InterruptedException {
        try {
            lock.lock(); //synchronized key word #monitor enter
            //如果没有使用就等待
            while (noUse) {
                try {
                    condition.await();//monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            data++;
            Optional.of("P:" + data).ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(1);
            noUse = true;
            //通知
            condition.signal();        //monitor.notify()
        } finally {
            lock.unlock();             //synchronized end
        }
    }

    private static void useData() {
        try {
            lock.lock();
            while (!noUse) {
                condition.await();
            }
            TimeUnit.SECONDS.sleep(1);
            Optional.of("C:" + data).ifPresent(System.out::println);
            //我已经使用了
            noUse = false;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            for (; ; ) {
                try {
                    buildData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (; ; ) {
                useData();
            }
        }).start();
    }
}
