package com.thread.three.automic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/27 9:23
 */
public class AtomicIntegerTest {

    /**
     * 1:可见性,有序性
     * 2：无法保证有序性
     */
    //private static volatile int value=0;

    public static void main(String[] args) throws InterruptedException {
       /* Thread t1 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    int tmp = value;
                    System.out.println(Thread.currentThread().getName() + " : " + tmp);
                    value += 1;
                    x++;
                    *//**
                     * value = value+1;
                     * (1)get value from main memory and to local memory
                     * (2)add 1=>x
                     * (3)assign the value to x
                     * (4)flush to main memory
                     *//*
                }

            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    int tmp = value;
                    System.out.println(Thread.currentThread().getName() + " : " + tmp);
                    value += 1;
                    x++;
                }

            }
        };
        Thread t3 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    int tmp = value;
                    System.out.println(Thread.currentThread().getName() + " : " + tmp);
                    value += 1;
                    x++;
                }

            }
        };
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();*/
        AtomicInteger value = new AtomicInteger();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    //新增
                    int tmp = value.getAndIncrement();
                    System.out.println(Thread.currentThread().getName() + " : " + tmp);
                    x++;
                }

            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    //新增
                    int tmp = value.getAndIncrement();
                    System.out.println(Thread.currentThread().getName() + " : " + tmp);
                    x++;
                }

            }
        };
        Thread t3 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    //新增
                    int tmp = value.getAndIncrement();
                    System.out.println(Thread.currentThread().getName() + " : " + tmp);
                    x++;
                }

            }
        };
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }


}
