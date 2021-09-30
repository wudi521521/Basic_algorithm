package com.concurrent.phase.thread.transaction;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/5 16:08
 */
public class TransactionTest {

    private final static CountDownLatch latch = new CountDownLatch(2);


    private static void A (CountDownLatch latchA,ThreadLocal localA) throws InterruptedException {

        try{
            latchA.countDown();
            System.out.println("A=======running");
            TimeUnit.SECONDS.sleep(1);
            localA.set(1);
        }catch (InterruptedException e){
            localA.set(0);
        }finally {
            System.out.println("A count"+latchA.getCount());
            latchA.await();
            System.out.println("A await local:"+localA.get());
        }
        System.out.println("A start");

    };

    private static void B (CountDownLatch latchB,ThreadLocal localB){

        try{
            latchB.countDown();
            System.out.println("B=======running");
            TimeUnit.SECONDS.sleep(1);
            localB.set(1);
           int i= 1/0;
        }catch (InterruptedException e){
            System.out.println("*************Exception");
            localB.set(0);
        }catch (ArithmeticException a){
            System.out.println("*************Exception");
            localB.set(0);
        }finally {
            System.out.println("B count"+latchB.getCount()+"thread name: ");
            try {
                latchB.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B await local:"+localB.get());
        }
        System.out.println("B start");

    };

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        ThreadLocal local = new ThreadLocal();

        service.execute(()->{
            try {
                A(latch, local);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.execute(()->{

                B(latch, local);

        });
        latch.await();
        System.out.println("===全部完成");
        service.shutdown();

    }
}
