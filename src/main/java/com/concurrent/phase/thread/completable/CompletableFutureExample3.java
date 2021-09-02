package com.concurrent.phase.thread.completable;

import java.sql.SQLOutput;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/2 16:37
 */
public class CompletableFutureExample3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       /* CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<String> whenComplete = future.whenComplete((v, t) -> System.out.println("done"));
        System.out.println(whenComplete.get());

*/

        /*CompletableFuture<String> not_get_the_data = CompletableFuture
                .supplyAsync((Supplier<String>) () -> {
                    throw new RuntimeException("not get the data");
                })
                .handleAsync((s, t) -> {
                    Optional.of(t).ifPresent(e->System.out.println(e));

                    return (s==null)?"I am error":s;
                });
        System.out.println(not_get_the_data.get());*/

        CompletableFuture.supplyAsync(()->"Hello").thenAccept((v)-> get(v));
    }

    private static void get(){
        System.out.println("end finished ");
    }

    private static void get(String data){
        System.out.println("end finished "+data);
    }
}