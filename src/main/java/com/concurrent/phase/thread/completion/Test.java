package com.concurrent.phase.thread.completion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/1 17:38
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        futureDe2();
    }

    private static void futureDe1() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(() -> {
            times(1);
            return 10;
        });
        System.out.println("========");
        System.out.println(future.get());
        System.out.println("+++++++++++");
    }

    private static void futureDe2() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        final List<Callable<Integer>> callableList = Arrays.asList(()->{
            times(1);
            System.out.println("======1");
            return 1;
        },()->{
            times(2);
            System.out.println("===========2");
            return 2;
        });
        List<Future<Integer>> futures = new ArrayList<>();
        futures.add(executorService.submit(callableList.get(0)));
        futures.add(executorService.submit(callableList.get(1)));

        //List<Future<Integer>> futures = executorService.invokeAll(callableList);
        Integer v1 = futures.get(1).get();
        System.out.println(v1);

        Integer v2 = futures.get(0).get();
        System.out.println(v2);
    }


    private static void times(int times) {
        try {
            TimeUnit.SECONDS.sleep(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
