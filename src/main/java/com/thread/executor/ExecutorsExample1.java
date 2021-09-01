package com.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/1 9:26
 */
public class ExecutorsExample1 {

    /**
     * These pools will typically improve the performance
     *  of programs that execute many short-lived asynchronous tasks
     * @param args
     *
     * return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
     *                                       60L, TimeUnit.SECONDS,
     *                                       new SynchronousQueue<Runnable>())
     */
    public static void main(String[] args) {
        //useCacheThreadPool();
        fixedSizePool();
    }

    /**
     * public static ExecutorService newSingleThreadExecutor() {
     *         return new FinalizableDelegatedExecutorService
     *             (new ThreadPoolExecutor(1, 1,
     *                                     0L, TimeUnit.MILLISECONDS,
     *                                     new LinkedBlockingQueue<Runnable>()));
     *     }
     */
    private static void useSinglePool(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
    }

    /**
     *nThreads, nThreads,
     *                                       0L, TimeUnit.MILLISECONDS,
     *                                       new LinkedBlockingQueue<Runnable>()
     *                                       线程不会回收(core 与 max 一样大)
     */
    private static void fixedSizePool(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount());
        executorService.execute(()->{
            System.out.println("======");
        });
        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount());

        IntStream.range(0,100).boxed().forEach(i->{
            executorService.execute(()->{
                times(2);
                System.out.println(Thread.currentThread().getName()+" {"+i+"}");
            });
        });
        times(2);
        System.out.println("it's all end");
    }

    /**
     * 适用场景是时间的短的请求
     * 线程会自动销毁(core)
     * 0, Integer.MAX_VALUE,
     *                                       60L, TimeUnit.SECONDS,
     *                                       new SynchronousQueue<Runnable>()
     */
    private static void useCacheThreadPool(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount());
        executorService.execute(()->{
            System.out.println("======");
        });
        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount());

        IntStream.range(0,100).boxed().forEach(i->{
            executorService.execute(()->{
                times(2);
                System.out.println(Thread.currentThread().getName()+" {"+i+"}");
            });
        });
        times(2);
        System.out.println("it's all end");
    }

    private static void times(int times){
        try {
            TimeUnit.SECONDS.sleep(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
