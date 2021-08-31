package com.thread.forjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/31 11:05
 */
public class ForkJoinRecursiveTask {

    private final static int MAX_THRESHOLD = 3;

    public static void main(String[] args) {
        final ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> future = forkJoinPool.submit(new CalculatedRecursiveTask(0, 10));
        try {
          Integer result =  future.get();
            System.out.println(" result :"+result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static class CalculatedRecursiveTask extends RecursiveTask<Integer>{
        //上标
        private final int start;

        //下标
        private final int end;

         CalculatedRecursiveTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            System.out.println("*****compute*******:");
             if(end-start<=MAX_THRESHOLD){
                 return IntStream.rangeClosed(start,end).sum();
             }else {
                 int middle = (start+end)/2;
                 CalculatedRecursiveTask leftTask = new CalculatedRecursiveTask(start,middle);
                 CalculatedRecursiveTask rightTask = new CalculatedRecursiveTask(middle+1,end);
                 leftTask.fork();
                 rightTask.fork();
                 return leftTask.join()+rightTask.join();
             }
        }
    }
}
