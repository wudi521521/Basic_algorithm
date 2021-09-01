package com.concurrent.phase.thread.three.automic.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/30 13:22
 */
public class SemaphoreExample1 {
    public static void main(String[] args) {
        final SemaphoreLock semaphore = new SemaphoreLock();
        new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+" is running");
                   semaphore.lock();
                    System.out.println(Thread.currentThread().getName()+" ;I'm get lock");
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    semaphore.unlock();
                    System.out.println(Thread.currentThread().getName()+" lock release");
                }
            }
        }.start();


    }
    static class SemaphoreLock{
        private final  Semaphore semaphore = new Semaphore(1);

        public void lock() throws InterruptedException{
            semaphore.acquire();
        }

        public void unlock(){
            semaphore.release();
        }
    }

    private synchronized static void m(){
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
