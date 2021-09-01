package com.concurrent.phase.thread.basic.chapter4;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/18 15:18
 */
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            IntStream.range(1,1000).
                    forEach(i-> System.out.println(Thread.currentThread().getName()+"->"+i));
        });
        Thread t2 = new Thread(()->{
            IntStream.range(1,1000).
                    forEach(i-> System.out.println(Thread.currentThread().getName()+"->"+i));
        });
        t1.start();
        t2.start();
        //等待t1线程执行完成,主线程才可以被唤醒
        t1.join();
        t2.join();
        Optional.of("All of tasks finish done").ifPresent(System.out::println);
        IntStream.range(1,1000).
                forEach(i-> System.out.println(Thread.currentThread().getName()+"->"+i));

    }
}
