package com.concurrent.phase.chapter2;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/22 11:19
 */
public class WaitSet {

    private static final Object LOCK = new Object();

    private static void work(){
        synchronized (LOCK){
            System.out.println("begin>>>>>>>>");
            try{
                System.out.println("Thread will coming");
                LOCK.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Out>>>>>>>>");

        }
    }

    /**
     * 1:所有对象都会有一个wait set，用来存放调用了该对象wait方法之后进入block状态线程
     * 2:线程被notify之后,不一定立即得到执行
     * 3:线程从wait set中 被唤醒顺序不一定是FIFO
     * 4：线程被唤醒后,必须重新获取锁
     * @param args
     */
    public static void main(String[] args) throws InterruptedException{
        new Thread(()->{
            work();
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            synchronized (LOCK){
                LOCK.notify();
            }
        }).start();

        IntStream.rangeClosed(1,10).forEach(item->{
            new Thread(String.valueOf(item)){
                @Override
                public void run() {
                    synchronized (LOCK){
                        try{
                            Optional.of(Thread.currentThread().getName()+" will come to wait set").ifPresent(System.out::println);
                            LOCK.wait();
                            Optional.of(Thread.currentThread().getName()+" will leave to wait set").ifPresent(System.out::println);

                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }

                    }
                }
            }.start();
        });

        Thread.sleep(3_000);
        //唤醒线程
        IntStream.rangeClosed(1,10).forEach(item->{
         synchronized (LOCK){
             try{
                 Optional.of(Thread.currentThread().getName()+" will notify").ifPresent(System.out::println);
                 LOCK.notify();
                 Thread.sleep(1_000);
             }catch (InterruptedException e){
                 e.printStackTrace();
             }
         }
        });
    }
}
