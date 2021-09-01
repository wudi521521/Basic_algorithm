package com.concurrent.phase.thread.three.automic.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/30 14:26
 */
public class SemaphoreExample4 {
    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(5);
        Thread t1 = new Thread(){
            @Override
            public void run() {
                try {
                    //有多少个令牌就获取多少个
                    semaphore.drainPermits();
                    TimeUnit.SECONDS.sleep(50);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    semaphore.release(5);
                }
                System.out.println("T1 finished");
            }
        };
        t1.start();

        Thread t2 = new Thread(){
            @Override
            public void run() {
                try {
                    //有多少个令牌就获取多少个
                    semaphore.drainPermits();
                    TimeUnit.SECONDS.sleep(50);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    semaphore.release(5);
                }
                System.out.println("T1 finished");
            }
        };
        t2.start();
        System.out.println(semaphore.hasQueuedThreads());
    }
}
