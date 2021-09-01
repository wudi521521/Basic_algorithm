package com.concurrent.phase.thread.basic.chapter3;

import java.util.Optional;

/**
 * @author Dillon Wu
 * @Description: 线程优先级
 * @date 2021/8/18 14:24
 */
public class ThreadSimpleAPI2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            for (int i = 0; i <1_000 ; i++) {
                Optional.of(Thread.currentThread().getName()+"-Index"+i).ifPresent(System.out::println);
            }
        });
        //最高优先级
        t1.setPriority(Thread.MAX_PRIORITY);
        Thread t2 = new Thread(()->{
            for (int i = 0; i <1_000 ; i++) {
                Optional.of(Thread.currentThread().getName()+"-Index2"+i).ifPresent(System.out::println);
            }
        });

        t2.setPriority(Thread.MIN_PRIORITY);
        t2.getPriority();
        Thread t3 = new Thread(()->{
            for (int i = 0; i <1_000 ; i++) {
                Optional.of(Thread.currentThread().getName()+"-Index3"+i).ifPresent(System.out::println);
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
