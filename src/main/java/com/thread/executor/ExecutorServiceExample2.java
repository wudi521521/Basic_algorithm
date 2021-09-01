package com.thread.executor;

import java.util.concurrent.*;

/**
 * @author Dillon Wu
 * @Description: 拒绝策略
 * @date 2021/9/1 13:48
 */
public class ExecutorServiceExample2 {
    public static void main(String[] args) {
        testDiscardPolicy();
    }

    private static void testAbortPolicy() {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                times(2);
            });
        }
        times(2);
        executorService.execute(()-> System.out.println("x"));

    }

    private static void testDiscardPolicy() {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                times(2);
            });
        }
        times(2);
        executorService.execute(()-> System.out.println("x"));

    }


    private static void times(int times) {
        try {
            TimeUnit.SECONDS.sleep(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
