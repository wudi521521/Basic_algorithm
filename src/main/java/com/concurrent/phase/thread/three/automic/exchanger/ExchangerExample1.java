package com.concurrent.phase.thread.three.automic.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/30 11:25
 */
public class ExchangerExample1 {
    public static void main(String[] args) {
        final Exchanger<String> exchanger = new Exchanger<>();

        /**
         * V r = exchange(V v)
         * indicate the object the current thread wanted transfer
         * indicate the other thread(pair) return transfer
         *
         * NOTE:
         * 1:if the pair thread not reached change point ,the thread will blocked,
         * 2:use the must be pair thread
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" start ");
                String exchange = null;
                try {
                    exchange = exchanger.exchange("I am come from T-A");
                    System.out.println(Thread.currentThread().getName()+" Get Value "+exchange);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"+++++++++A++++++++++").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" start ");
                String exchange = null;
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                    exchange = exchanger.exchange("I am come from T-B");
                    System.out.println(Thread.currentThread().getName()+" Get Value "+exchange);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"===B===").start();
    }
}
