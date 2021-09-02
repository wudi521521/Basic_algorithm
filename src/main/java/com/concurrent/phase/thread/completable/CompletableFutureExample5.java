package com.concurrent.phase.thread.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/2 17:50
 */
public class CompletableFutureExample5 {
    public static void main(String[] args) {
        complete();
    }



    private static void complete(){
        boolean world = CompletableFuture.supplyAsync(() -> {
            times(5);
            return "Hello";
        }).complete("World");
        times(3);
        System.out.println(world);

    }

    private static void getNow(){
        String world = CompletableFuture.supplyAsync(() -> {
            times(2);
            return "Hello";
        }).getNow("World");
        System.out.println(world);
    }

    private static void times(int times) {
        try {
            TimeUnit.SECONDS.sleep(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
