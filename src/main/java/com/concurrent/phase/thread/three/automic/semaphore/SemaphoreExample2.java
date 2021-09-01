package com.concurrent.phase.thread.three.automic.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/30 13:42
 */
public class SemaphoreExample2 {
    /**
     * connection pool
     * when get the not available connection/policy
     * 1:Get 1000ms then throw exception
     * 2:blocking
     * 3:discard
     * 4:Get then throw exception
     * 5:get->register the callback->
     * @param args
     */

    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(2);
        for (int i=0;i<2;i++){
            new Thread(){
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+" in");
                    try{
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName()+" Get the semaphore.");
                        TimeUnit.SECONDS.sleep(5);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }finally {
                        semaphore.release();
                    }
                    System.out.println(Thread.currentThread().getName()+" out");
                }
            }.start();
            while (true){
                try {
                    //可用的许可证
                    System.out.println(semaphore.availablePermits());
                    System.out.println(semaphore.getQueueLength());
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
