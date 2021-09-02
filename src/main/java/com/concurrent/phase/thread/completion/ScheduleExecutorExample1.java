package com.concurrent.phase.thread.completion;

import java.util.concurrent.*;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/2 9:57
 */
public class ScheduleExecutorExample1
{

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(2);
       /* ScheduledFuture<?> future = executorService.schedule(()->
            System.out.println("I will invoked"),4,TimeUnit.SECONDS);

        System.out.println(future.cancel(true));*/

        ScheduledFuture<Integer> future = executorService.schedule(() -> 2, 2, TimeUnit.SECONDS);
        System.out.println(future.get());
       //初次执行1s后,以后每隔2s执行
        executorService.scheduleAtFixedRate(()-> System.out.println("I am running"),1,2,TimeUnit.SECONDS);
    }
}
