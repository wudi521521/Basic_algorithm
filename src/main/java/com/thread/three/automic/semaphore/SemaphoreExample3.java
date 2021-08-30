package com.thread.three.automic.semaphore;

import java.sql.Time;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/30 14:14
 */
public class SemaphoreExample3 {
    /**
     * public void acquire(int permits);
     * public void release(int permits);
     * availablePermits()
     * getQueueLength
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(1);
        Thread t1 = new Thread(){
            @Override
            public void run() {
                try{
                    semaphore.acquire();
                    TimeUnit.SECONDS.sleep(5);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }
        };
        t1.start();

        TimeUnit.MILLISECONDS.sleep(50);

        Thread t2 = new Thread(){
            @Override
            public void run() {
                try{
                    semaphore.acquire();
                    TimeUnit.SECONDS.sleep(5);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }
        };
        t2.start();
        t2.interrupt();
    }
}
