package com.concurrent.phase.thread.basic.chapter5;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/18 21:22
 */
public class SynchronizeTest {
    private final static Object MONITOR = new Object();

    public static void main(String[] args) {
        Runnable runnable = ()->{
            synchronized (MONITOR){
                try {
                    Thread.sleep(100_000);
                }catch (InterruptedException e){

                }
            }
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        t1.start();
        t2.start();
        t3.start();
    }

}
