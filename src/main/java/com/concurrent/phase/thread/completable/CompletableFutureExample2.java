package com.concurrent.phase.thread.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/2 15:50
 */
public class CompletableFutureExample2 {

    public static void main(String[] args) throws InterruptedException {
        supplyAsync();
        //runAsync();
        //Thread.currentThread().join();
        //Future<Void> future = completed("Hello");
        //System.out.println(future.isDone());
        //anfOf();
        Thread.currentThread().join();
    }

    private static void anfOf(){
        CompletableFuture.anyOf(CompletableFuture.completedFuture("hello").thenAccept(System.out::println ).whenComplete((v,t)-> System.out.println(v)));
    }


    private static Future<Void> completed(String data){
        return CompletableFuture.completedFuture(data).thenAccept(System.out::println ).whenComplete((v,t)-> System.out.println(v));
    }



    /**
     *
     */
    private static void runAsync(){
        CompletableFuture.runAsync(()->{
            System.out.println("Obj=============Start");
            times(2);
            System.out.println("Obj=============End");
        }).whenCompleteAsync((v,t)->get());
    }

    /**
     * post->{
     *     basic
     *     details
     * }
     * insert basic
     * insert details
     *               insert basic
     * [submit]      insert details        ======> action
     */
    private static void supplyAsync(){
        CompletableFuture.supplyAsync(Object::new)
                .thenAccept(Obj->{
                    System.out.println("obj====start");
                    times(3);
                    System.out.println("obj "+Obj);
                })
                .runAfterBoth(CompletableFuture.supplyAsync(()->"Hello")
                        .thenAccept(s -> {
                            System.out.println("string=====start");
                            times(2);
                            int number=1/0;
                            System.out.println("String===="+s);
                        }),()-> System.out.println("=======Finished")).whenComplete(((aVoid, throwable) -> get()));
    }

    private static void times(int times) {
        try {
            TimeUnit.SECONDS.sleep(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void get(){
        System.out.println("end finished ");
    }

    private static void get(String data){
        System.out.println("end finished ");
    }
}
