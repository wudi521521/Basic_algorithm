package com.concurrent.phase.thread.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/31 15:43
 */
public class ThreadPoolExecutorBuild {
    public static void main(String[] args) {
        ThreadPoolExecutor  executorService = buildThreadPoolExecutor();
        int activeCount = -1;
        int queueSize=-1;
        while (true){
            if (activeCount !=executorService.getActiveCount() || queueSize !=executorService.getQueue().size()){
                System.out.println(executorService.getActiveCount());
                System.out.println(executorService.getCorePoolSize());
                System.out.println(executorService.getQueue().size());
                System.out.println(executorService.getMaximumPoolSize());
                activeCount=executorService.getActiveCount();
                queueSize=executorService.getQueue().size();
                System.out.println("============================================");
            }

        }
    }

    /**
     * Testing point
     * <p>
     *    1: coreSize =1,Maxsize=2, blockingQueue is empty,what happen when  submit three task?
     *    2: coreSize =1,Maxsize=2,blockingQueue size=5, what happen when submit 7 task?
     *    3: coreSize=1,Maxsize=2,blockingQueue size=5, what happen submit 8 task?
     * </p>
     * int corePoolSize, 核心线程数
     * int maximumPoolSize, 最大线程数
     * long keepAliveTime,
     * TimeUnit unit,
     * BlockingQueue<Runnable> workQueue
     */
    private static ThreadPoolExecutor buildThreadPoolExecutor() {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.AbortPolicy());
        System.out.println("The ThreadPoolExecutor create done");

        executorService.execute(()->sleepSeconds(10));
        executorService.execute(()->sleepSeconds(1));
        executorService.execute(()->sleepSeconds(1));
        //executorService.execute(()->sleepSeconds(100));

        return executorService;
    }

    private static void sleepSeconds(int seconds){
        try {
            System.out.println("*** "+Thread.currentThread().getName()+" **");
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
