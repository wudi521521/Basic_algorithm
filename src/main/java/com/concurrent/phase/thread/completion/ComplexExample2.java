package com.concurrent.phase.thread.completion;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/2 13:32
 */
public class ComplexExample2 {
    public static void main(String[] args) throws InterruptedException {
        final ExecutorService service = Executors.newSingleThreadExecutor();
        List<Runnable> tasks = IntStream.range(0,5)
                .boxed().map(ComplexExample2::toTask).collect(toList());
        final CompletionService<Object> completionService = new ExecutorCompletionService<>(service);

        tasks.forEach(r->completionService.submit(Executors.callable(r)));
        TimeUnit.SECONDS.sleep(20);
        List<Runnable> runnables = service.shutdownNow();
        //获取未执行的任务
        System.out.println(runnables.size());
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
