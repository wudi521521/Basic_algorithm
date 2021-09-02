package com.concurrent.phase.thread.completion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/1 17:55
 */
public class CompletionExample1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        final List<Callable<Integer>> callables = Arrays.asList(
                () -> {
                    times(2);
                    System.out.println("The 10 finished");
                    return 20;
                }, () -> {
                    times(1);
                    System.out.println("The 20 finished");
                    return 10;
                }
        );

        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);
       List<Future<Integer>>  futures = new ArrayList<>();
       callables.stream().forEach(item->{
           futures.add(completionService.submit(item));
       });
        //先结束的放到queue队列中

       /* Future<Integer> future ;
        while ((future =completionService.take())  !=null){
            System.out.println(future.get());
        }
*/
        Future<Integer> future1 = completionService.poll(3,TimeUnit.SECONDS );
        System.out.println(future1);

    }

    private static void times(int times) {
        try {
            TimeUnit.SECONDS.sleep(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

