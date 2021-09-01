package com.concurrent.phase.thread.three.automic.countdownlatch;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Dillon Wu
 * @Description: countDownLatch使用场景
 * @date 2021/8/28 14:12
 */
public class CountDownLatchExample1 {
    private static Random random = new Random(System.currentTimeMillis());

    private static ExecutorService executor = Executors.newFixedThreadPool(2);

    private static final CountDownLatch latch = new CountDownLatch(10);


    public static void main(String[] args) throws InterruptedException {
        //1:
         int[] data = query();
        //2:
        for (int i=0;i<data.length;i++){
            executor.execute(new SimpleRunnable(data,i,latch));
        }
        //3:
        latch.await();
        //当所有的线程执行完成才可以结束
        executor.shutdown();
        //结束以后或者1小时以后
        //executor.awaitTermination(1, TimeUnit.HOURS);

        System.out.println("all of work ");
    }

    static class SimpleRunnable implements Runnable{
        private final int[] data;

        private final int index;

        private final CountDownLatch latch;

        SimpleRunnable(int[] data,int index,CountDownLatch latch){
            this.data=data;
            this.index=index;
            this.latch=latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(2_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int value = data[index];
            if (value%2==0){
                data[index]=value*2;
            }else {
                data[index]=value*10;
            }
            System.out.println(Thread.currentThread().getName()+"finished");
            System.out.println("*******countDown*******"+latch.getCount());
            //计算减少
            latch.countDown();
        }
    }

    private static int[] query() {
        return new int[]{1, 2, 3, 4, 5, 6, 7, 8,9,10};
    }
}
