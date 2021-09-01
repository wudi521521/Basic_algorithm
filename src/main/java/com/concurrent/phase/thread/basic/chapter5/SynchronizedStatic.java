package com.concurrent.phase.thread.basic.chapter5;

/**
 * @author Dillon Wu
 * @Description: 静态同步方法
 * @date 2021/8/19 10:10
 */
public class SynchronizedStatic {

    private static final Object MONITOR = new Object();

    static {
        //添加的是类锁
        synchronized (SynchronizedStatic.class){
            try {
                System.out.println("static:"+Thread.currentThread().getName());
                Thread.sleep(10_000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static synchronized void m1(){
        System.out.println("m1==="+Thread.currentThread().getName());
        try{
            Thread.sleep(10_000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public synchronized static void m2(){
        System.out.println("m2==="+Thread.currentThread().getName());
        try{
            Thread.sleep(10_000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
