package com.concurrent.phase.thread.completion;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/2 13:09
 */
public class ComplexExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);
        List<Runnable> tasks = IntStream.range(0, 5).boxed().map(ComplexExample::toTask).collect(toList());
        List<Future<?>> futureList = new ArrayList<>();
        final CompletionService<Object> completionService=new ExecutorCompletionService<>(service);
        tasks.forEach(r->{
            completionService.submit(Executors.callable(r));
        });

        /*tasks.forEach(r->{
            futureList.add(service.submit(r));
        });*/
        Future<?> future;
        while ((future=completionService.take())!=null){
            System.out.println(future.get());
        }

       /* futureList.get(4).get();
        System.out.println("======4======");
        futureList.get(3).get();
        System.out.println("======3======");
        futureList.get(2).get();
        System.out.println("======2======");
        futureList.get(1).get();
        System.out.println("======1======");
        futureList.get(0).get();
        System.out.println("======0======");*/
    }

    private static Runnable toTask(int i){
        return ()->{
            try {
                System.out.printf("The Task[%d] will be executed \n",i);
                TimeUnit.SECONDS.sleep(i*5+10);
                System.out.printf("The Task[%d] execute done \n",i);
            } catch (InterruptedException e) {
                System.out.printf("The Task[%d] be interrupted \n",i);
            }
        };
    }
}
