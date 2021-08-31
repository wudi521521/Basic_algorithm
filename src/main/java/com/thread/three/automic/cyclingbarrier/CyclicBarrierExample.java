package com.thread.three.automic.cyclingbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/30 8:32
 */
public class CyclicBarrierExample {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cylicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("all of finished");
            }
        });
        new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                    System.out.println("T1 finished");
                    System.out.println("T1 The other thread finished too");
                    cylicBarrier.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }catch (BrokenBarrierException e){
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                    System.out.println("T2 finished");
                    cylicBarrier.await();
                    System.out.println("T2 The other thread finished too");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }catch (BrokenBarrierException e){
                    e.printStackTrace();
                }
            }
        }.start();
        while(true){
            System.out.println("main: "+cylicBarrier.getNumberWaiting());
            System.out.println("main: "+cylicBarrier.getParties());
            System.out.println("main: "+cylicBarrier.isBroken());
        }

    }
}
