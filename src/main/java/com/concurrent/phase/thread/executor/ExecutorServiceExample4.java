package com.concurrent.phase.thread.executor;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/1 15:40
 */
public class ExecutorServiceExample4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //ExecutorService executorService = Executors.newFixedThreadPool(10);
        //testInvokerAny();
        //testSubmitRunnable();
        testSubmitRunnableWithResult();
    }

    /**
     *Question:
     * when the result returned ,other callable will be keep on process
     * NOTE:
     * The invokeAny will be blocked caller;
     */
    private static void  testInvokerAny() throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> callables = IntStream.range(0, 5).boxed().map(
                i -> (Callable<Integer>) () -> {
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
                    return i;
                }
        ).collect(Collectors.toList());
        Integer value = executorService.invokeAny(callables);
        System.out.println("==========finished=========="+value);

    }

    private static void testSubmitRunnable() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<?> future = executorService.submit(() -> {
            times(1);
        });

        System.out.println(future.get());
    }

    private static void testSubmitRunnableWithResult() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        String result = "DONE";
        Future<?> future = executorService.submit(() -> {
            times(1);
        },result);
        System.out.println(future.get());

    }

    private static void times(int times) {
        try {
            TimeUnit.SECONDS.sleep(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
