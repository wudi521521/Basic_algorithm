package com.thread.basic.simple.chapter7;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/19 16:37
 */
public class LockTest {
    public static void main(String[] args) throws InterruptedException{
       final BooleanLock booleanLock = new BooleanLock();
        Stream.of("T1","T2","T3").forEach(item->{
            new Thread(()->{
            try {
                //获取锁
                booleanLock.lock();
                Optional.of(Thread.currentThread().getName()+" have the lock Monitor")
                        .ifPresent(System.out::println);
                //工作
                work();
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                //解锁
                booleanLock.unlock();
            }
            },item).start();
        });
        Thread.sleep(100);
        booleanLock.unlock();
    }

    private static void work() throws InterruptedException{
        Optional.of(Thread.currentThread().getName()+" is working").ifPresent(System.out::println);
        Thread.sleep(10_000);
    }
}
