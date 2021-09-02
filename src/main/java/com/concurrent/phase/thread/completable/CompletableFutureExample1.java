package com.concurrent.phase.thread.completable;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/2 13:59
 */
public class CompletableFutureExample1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       /* ExecutorService executor = Executors.newFixedThreadPool(10);

        //有返回接口,future，但是future的get()是阻塞的
        Future<?> future = executor.submit(() -> {
            times(1);
        });
        //future.isDone() 为true表示完成,false未完成
        while (!future.isDone()){
            System.out.println("未完成");
        }
        System.out.println("Done");*/
       /* CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            times(1);
            //完成后调用接口
        }).whenComplete((v, t) -> System.out.println("Done"));
        System.out.println("----------------");
        Thread.currentThread().join();*/
       /*ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Callable<Integer>> tasks = IntStream.range(0, 10).boxed()
                .map(i -> (Callable<Integer>) () -> get()).collect(toList());


        executorService.invokeAll(tasks).stream().map(future->{
            try {
                return future.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).parallel().forEach(CompletableFutureExample1::display);
*/
        IntStream.range(0, 10).boxed()
                .forEach(i -> CompletableFuture.supplyAsync(CompletableFutureExample1::get)
                        .thenAccept(CompletableFutureExample1::display)
                        .whenComplete((v,t)-> System.out.println(i+" DONE"))
                );
        Thread.currentThread().join();

    }

    private static void display(int data){

        System.out.println(Thread.currentThread().getName()+ " display will be sleep "+3);
        times(2);
        System.out.println(Thread.currentThread().getName()+ " execute done "+data);
    }

    private static int get(){
        times(2);
        System.out.println(Thread.currentThread().getName()+ " get will be sleep "+2);
        return 11;
    }

    private static void times(int times) {
        try {
            TimeUnit.SECONDS.sleep(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
