package com.concurrent.phase.thread.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/2 17:17
 */
public class CompletableFutureExample4 {
    public static void main(String[] args) throws InterruptedException {
       // thenAcceptBoth();
        //runAfterEither();
        compose();
        Thread.currentThread().join();
    }

    private static void compose(){
        CompletableFuture.supplyAsync(()->{
            System.out.println("Start the runAfterBoth");

            System.out.println("end the runAfterBoth");
            return "acceptEither-1";
        }).thenCompose(s->CompletableFuture.supplyAsync(()->{
            System.out.println("Start the runAfterBoth");

            System.out.println("end the runAfterBoth");
            return "hello " +s;
        })).whenComplete((v,t)->System.out.println(v));
    }

    private static void combine(){
        CompletableFuture.supplyAsync(()->{
            System.out.println("Start the runAfterBoth");

            System.out.println("end the runAfterBoth");
            return "acceptEither-1";
        }).thenCombine( CompletableFuture.supplyAsync(()->{
            System.out.println("Start the runAfterBoth");

            System.out.println("end the runAfterBoth");
            return "acceptEither-1";
        }),(s,i)-> true);
    }

    private static void runAfterEither(){
        CompletableFuture.supplyAsync(()->{
            System.out.println("Start the runAfterBoth");

            System.out.println("end the runAfterBoth");
            return "acceptEither-1";
        }).runAfterEitherAsync(
                CompletableFuture.supplyAsync(()->{
                    System.out.println("***Start the runAfterBoth");
                    times(4);
                    System.out.println("***end the runAfterBoth");
                    return "acceptEither-1";
                }),()->{
                    System.out.println("DONE");}
        );

    }

    private static void runAfterBoth(){
        CompletableFuture.supplyAsync(()->{
            System.out.println("Start the runAfterBoth");

            System.out.println("end the runAfterBoth");
            return "acceptEither-1";
        }).runAfterBothAsync(
                CompletableFuture.supplyAsync(()->{
                    System.out.println("***Start the runAfterBoth");
                    times(4);
                    System.out.println("***end the runAfterBoth");
                    return "acceptEither-1";
                }),()->{
                    System.out.println("DONE");}
                );

    }

    private static void thenAcceptBoth(){
        CompletableFuture.supplyAsync(()->{
            System.out.println("Start the supplyAsync");
            times(2);
            System.out.println("End the supplyAsync");
            return "thenAcceptBoth";
        }).thenAcceptBoth( CompletableFuture.supplyAsync(()->{
            System.out.println("Start the then--supplyAsync");
            times(2);
            System.out.println("End the then--supplyAsync");
            return "then--thenAcceptBoth";
        }),(s,i)-> System.out.println(s+"--------------"+i)).thenAcceptBoth(
                CompletableFuture.supplyAsync(()->{
                    System.out.println("Start the then***********supplyAsync");
                    times(2);
                    System.out.println("End the then*********supplyAsync");
                    return "then-**********-thenAcceptBoth";
                }),(s,i)-> System.out.println(s+"***************"+i)
        );
    }

    private static void times(int times) {
        try {
            TimeUnit.SECONDS.sleep(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
