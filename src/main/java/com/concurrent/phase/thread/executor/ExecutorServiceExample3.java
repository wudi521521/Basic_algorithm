package com.concurrent.phase.thread.executor;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/1 14:06
 */
public class ExecutorServiceExample3 {

    public static void main(String[] args) {
//test();
        //testRemove();
        //testPestartCoreThread();
        testThreadPoolAdvice();
    }

    private static void test() {
        ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        System.out.println(service.getActiveCount());
        service.execute(() -> {
            times(1);
        });
        times(1);
        System.out.println(service.getActiveCount());

    }

    private static void testAllowCoreThreadTimeOut() {
        ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        service.setKeepAliveTime(10, TimeUnit.SECONDS);
        //Core threads must have nonzero keep alive times
        service.allowCoreThreadTimeOut(true);
        IntStream.range(0, 5).boxed().forEach(i -> {
            service.execute(() -> {
                times(3);
            });
        });
    }

    private static void testRemove() {
        ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        service.setKeepAliveTime(10, TimeUnit.SECONDS);
        //Core threads must have nonzero keep alive times
        service.allowCoreThreadTimeOut(true);
        IntStream.range(0, 5).boxed().forEach(i -> {
            service.execute(() -> {
                times(3);
                System.out.println("================I am finished");
            });
        });
        Runnable r = () -> {
            System.out.println("I will never be executed");
        };
        //core 数量已经达到上线,任务进入队列
        service.execute(r);
        service.remove(r);
    }

    private static void testPestartCoreThread() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        System.out.println(executor.getActiveCount());

        System.out.println(executor.prestartCoreThread());
        System.out.println(executor.getActiveCount());

        System.out.println(executor.prestartCoreThread());
        System.out.println(executor.getActiveCount());

        System.out.println(executor.prestartCoreThread());
        System.out.println(executor.getActiveCount());

        executor.execute(() -> {
            times(1);
        });

        executor.execute(() -> {
            times(1);
        });
        //有空闲线程返回true
        System.out.println(executor.prestartCoreThread());
        System.out.println(executor.getActiveCount());

    }

    private static void testThreadPoolAdvice() {
        ThreadPoolExecutor executorService = new MyThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.DiscardPolicy());

        executorService.execute(new MyRunnable(1){

        });

    }

    private static class MyRunnable implements Runnable {

        private final int no;

        public MyRunnable(int no) {
            this.no = no;
        }

        protected int getData() {
            return this.no;
        }

        @Override
        public void run() {
            System.out.println("=======================");
        }
    }

    private static class MyThreadPoolExecutor extends ThreadPoolExecutor {


        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            System.out.println("init the"+((MyRunnable)r).getData());
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            if (null==t){
                System.out.println("successfully"+((MyRunnable)r).getData());

            }else {
                System.out.println("fail"+((MyRunnable)r).getData());

            }
        }
    }

    private static void times(int times) {
        try {
            TimeUnit.SECONDS.sleep(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
