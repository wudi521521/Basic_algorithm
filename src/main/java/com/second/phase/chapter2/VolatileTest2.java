package com.second.phase.chapter2;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/22 13:43
 */
public class VolatileTest2 {

    private static int INIT_VALUE=0;

    private final static int MAX=50;
    public static void main(String[] args) {

        new Thread(()->{
            while (INIT_VALUE<MAX){
                System.out.println("T1->"+(++INIT_VALUE));
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        },"ADD-2").start();

        new Thread(()->{
            while (INIT_VALUE<MAX){
                System.out.println("T2->"+(++INIT_VALUE));
                try {
                    Thread.sleep(100);

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"ADD-2").start();
    }
}
