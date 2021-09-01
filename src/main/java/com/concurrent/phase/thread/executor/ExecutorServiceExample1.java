package com.concurrent.phase.thread.executor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/1 11:20
 */
public class ExecutorServiceExample1 {

    public static void main(String[] args) throws InterruptedException {
        // isTerminated();
        executeRunnableError();
    }

    /**
     * when invoked the shutdown method,can execute the new runnable
     */
    private static void isShutDown() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            times(2);
        });
        System.out.println(executorService.isShutdown());
        executorService.shutdown();

        executorService.execute(() -> System.out.println("again"));
    }

    private static void times(int times) {
        try {
            TimeUnit.SECONDS.sleep(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    private static void isTerminated() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            times(1);
        });
        executorService.shutdown();
        //是否关闭
        System.out.println(executorService.isShutdown());
        System.out.println(executorService.isTerminated());
        //是否终结(异步处理)正在终结
        System.out.println((((ThreadPoolExecutor) executorService)).isTerminating());

    }

    private static void executeRunnableError() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(19, new MyThreadFactory());
        executorService.execute(() -> {
            times(1);
        });
        IntStream.range(0, 10).boxed().forEach(i -> executorService.execute(
                new MyTask(i) {
                    @Override
                    protected void error(Throwable cause) {
                        System.out.println("The no:"+i+" failed,update status to ERROR");
                    }

                    @Override
                    protected void doInit() {

                    }

                    @Override
                    protected void doExecute() {
                       if (i%3==0){
                           System.out.println("do execute"+1/0);
                       }
                    }

                    @Override
                    protected void done() {
                        System.out.println("The no:"+i+" successfully , update status to DONE");

                    }
                }

        ));
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("===========");
    }

    private static class MyThreadFactory implements ThreadFactory {

        private final static AtomicInteger SEQ = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("My-Thread" + SEQ.getAndIncrement());
            thread.setUncaughtExceptionHandler((t, cause) -> {
                System.out.println("The thread" + t.getName() + " execute failed");
            });
            return thread;
        }
    }

    /**
     * send request--->store db--->10--->\
     */
    private static void executeRunnableTask() {

    }

    private abstract static class MyTask implements Runnable {

        private final int no;

        private MyTask(int no) {
            this.no = no;
        }

        @Override
        public void run() {
            try {
                this.doInit();
                this.doExecute();
                this.done();
            } catch (Throwable cause) {
                this.error(cause);
            }
        }
        protected abstract void error(Throwable cause);

        protected abstract void doInit();

        protected abstract void doExecute();

        protected abstract void done();
    }


}
