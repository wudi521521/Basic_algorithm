package com.thread.advance.chapter3;

import java.util.function.Consumer;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/23 14:28
 */
public class FutureService {

    public <T> Future<T> submit(final FutureTask<T> task){
        System.out.println("submit begin");
           AsynFuture<T> asynFuture = new AsynFuture<>();
           new Thread(()->{
               System.out.println("submit ing");
               //任务调用
               T result = task.call();
               //任务调用完成--->异步处理
               asynFuture.done(result);
           }).start();
        System.out.println("submit end");
           return asynFuture;
    }

    public <T> Future<T> submit(final FutureTask<T> task, final Consumer<T> consumer){
        System.out.println("submit begin");
        AsynFuture<T> asynFuture = new AsynFuture<>();
        new Thread(()->{
            System.out.println("submit ing");
            //任务调用
            T result = task.call();
            //任务调用完成--->异步处理
            asynFuture.done(result);
            //完成后条用消费者接口
            consumer.accept(result);
        }).start();
        System.out.println("submit end");
        return asynFuture;
    }
}
