package com.concurrent.phase.thread.advance.chapter5;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/23 19:51
 */
public class ComplexThreadLocalTest {

    private final static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException{
        Thread thread = new Thread(() -> {
            try{
                threadLocal.set("Thread t");
                Thread.sleep(10_000);
                System.out.println(Thread.currentThread().getName()+" "+threadLocal.get());
            }catch (InterruptedException e){}

        },"T1");
        thread.start();

        Thread thread2 = new Thread(() -> {
            try{
                threadLocal.set("Thread t");
                Thread.sleep(10_000);
                System.out.println(Thread.currentThread().getName()+" "+threadLocal.get());
            }catch (InterruptedException e){}

        },"T2");
        thread2.start();
        thread.join();
        thread2.join();
        System.out.println("===================");
        System.out.println(Thread.currentThread().getName()+" "+threadLocal.get());

    }
}
