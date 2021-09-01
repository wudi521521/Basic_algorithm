package com.concurrent.phase.thread.executor;

import java.util.concurrent.*;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/1 17:18
 */
public class FutureExample2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        Future<Integer> future = executor.submit(() -> {
            times(1);
            return 10;
        });

        System.out.println(future.get());
        //完全返回为true
        System.out.println(future.isDone());
    }

    private static void times(int times) {
        try {
            TimeUnit.SECONDS.sleep(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
