package com.thread.three.automic.cyclingbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/30 8:55
 */
public class CyclicBarrierExample2 {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("T1 ");
                    TimeUnit.SECONDS.sleep(5);
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("T2 ");
                    //TimeUnit.SECONDS.sleep(5);
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(cyclicBarrier.getNumberWaiting());
        System.out.println(cyclicBarrier.getParties());
        System.out.println(cyclicBarrier.isBroken());
        cyclicBarrier.reset();
        System.out.println(cyclicBarrier.getNumberWaiting());
        System.out.println(cyclicBarrier.getParties());
        System.out.println(cyclicBarrier.isBroken());
    }
}
