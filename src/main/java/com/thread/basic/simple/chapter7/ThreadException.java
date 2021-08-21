package com.thread.basic.simple.chapter7;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/20 10:45
 */
public class ThreadException {

    private final static int A =10;

    private final static int B=0;

    public static void main(String[] args) {

       Thread t= new Thread(()->{
            try {
                Thread.sleep(5_000);
                //int result =A/B;
                System.out.println(A);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        });
       //捕获线程
       t.setUncaughtExceptionHandler((thread,e)->{
           System.out.println(e);
           System.out.println(thread);
       });
       t.start();
    }
}
