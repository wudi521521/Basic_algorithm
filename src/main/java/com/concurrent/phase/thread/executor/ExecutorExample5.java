package com.concurrent.phase.thread.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/1 16:49
 */
public class ExecutorExample5 {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        executor.execute(()->{
            System.out.println("I will be process because");
        });
        executor.getQueue().add(()-> System.out.println("I am added directly into the queue"));

    }
}
