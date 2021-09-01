package com.thread.executor;



import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/1 10:13
 */
public class ExecutorsExample2 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newWorkStealingPool();
        //Optional.of(Runtime.getRuntime().availableProcessors()).ifPresent(System.out::println);
        /*Callable<String> callable =()->{
            return "Task -1";
        };*/
        List<Callable<String>> collect = IntStream.range(0, 20).boxed().map(i ->
                (Callable<String>) () -> {
                    System.out.println(Thread.currentThread().getName()+" ["+i+"]");
                    times(1);
                    return "Task-" + 1;
                }
        ).collect(Collectors.toList());

        List<Future<String>> futures = executorService.invokeAll(collect);
        futures.stream().forEach(item->{
            try {
                System.out.println(item.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    private static void times(int times){
        try {
            TimeUnit.SECONDS.sleep(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
