package com.concurrent.phase.thread.three.automic.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/28 16:45
 */
public class CountDownLatchExample3 {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        //主线程
        final Thread mainThread =Thread.currentThread();
      /*  latch.await();
        System.out.println("--------------");*/
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2_000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                latch.countDown();
            }
        }.start();
        latch.await(1000, TimeUnit.MILLISECONDS);
        System.out.println("=====================");
    }
}
