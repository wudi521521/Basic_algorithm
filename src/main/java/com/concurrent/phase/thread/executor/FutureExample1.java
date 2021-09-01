package com.concurrent.phase.thread.executor;

import java.util.concurrent.*;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/1 16:54
 */
public class FutureExample1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        Future<Integer> future = executor.submit(() -> {
            times(10);
            return 10;
        });

        /*Thread callerThread = Thread.currentThread();
        new Thread(() -> {
            callerThread.interrupt();
        }).start();*/
        System.out.println("============" + future.get(5,TimeUnit.SECONDS));
        System.out.println();
    }

    private static void times(int times) {
        try {
            TimeUnit.SECONDS.sleep(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
