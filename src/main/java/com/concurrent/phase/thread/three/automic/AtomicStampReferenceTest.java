package com.concurrent.phase.thread.three.automic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/27 22:03
 */
public class AtomicStampReferenceTest {

    private static AtomicStampedReference<Integer> atomicRef = new AtomicStampedReference<Integer>(100,0);

    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    TimeUnit.SECONDS.sleep(1);
                    boolean b = atomicRef.compareAndSet(100, 101, atomicRef.getStamp(), atomicRef.getStamp() + 1);
                    System.out.println(Thread.currentThread().getName()+"  "+b);
                    boolean b1 = atomicRef.compareAndSet(101, 100, atomicRef.getStamp(), atomicRef.getStamp() + 1);
                    System.out.println(Thread.currentThread().getName()+"   "+b1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    int stamp = atomicRef.getStamp();
                    TimeUnit.SECONDS.sleep(1);
                    boolean b = atomicRef.compareAndSet(100, 101, stamp, atomicRef.getStamp() + 1);
                    System.out.println(b);
                    //atomicRef.compareAndSet(101,100,stamp,atomicRef.getStamp()+1);

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
