package com.thread.executor;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/31 17:43
 */
public class ThreadPoolExecutorTask {
    public static void main(String[] args) {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.AbortPolicy());

        IntStream.range(0,20).boxed().forEach(i->{
            executorService.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName()+"["+i+"] finish done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });

        try{
            List<Runnable> runnables = executorService.shutdownNow();
            System.out.println(runnables.size());
        }catch (Exception e){
            e.printStackTrace();
        }

        //非阻塞
        //executorService.shutdown();
        /*try {
            //10内才结束，线程结束了，但是时间没有到还会继续等待
            executorService.awaitTermination(100,TimeUnit.SECONDS);
            System.out.println("===============");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        /**
         * shutdown
         * 20 threads
         *    10 threads work
         *    10 idle
         * shutdown invoked
         *    20 interruped
         *    10 idel threads will exist  空闲的线程退出
         *    10 waiting to finished the work 结束工作
         */
        /**
         * shutDownNow
         * 10  core threads
         * 10 threads queue elements 10
         * 10 running
         * 10 stored in the blocking queue
         *
         * 1: return list<Runnable> remain un handle runnable
         * 2:still work for the runnable by core thread
         */

    }
}
