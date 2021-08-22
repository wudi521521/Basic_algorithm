package com.second.phase.chapter2;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/22 12:19
 */
public class VolatileTest {

    private  volatile static int INIT_VALUE=0;

    private final static int MAX=50;
    public static void main(String[] args) {

       new Thread(()->{
           int localValue = INIT_VALUE;
           while (localValue<MAX){
               while (localValue<MAX){
                   if (localValue !=INIT_VALUE){
                       System.out.printf("The value updated11 to[%d]",INIT_VALUE);
                       localValue=INIT_VALUE;
                   }
               }
           }
       },"READER").start();

       new Thread(()->{
           int localValue = INIT_VALUE;
           while (INIT_VALUE<MAX){
               System.out.printf("Update the value to [%d]\n",++localValue);
               INIT_VALUE=localValue;
               System.out.printf("Update the value to [%d]\n",INIT_VALUE);
               try {
                   Thread.sleep(500);

               }catch (InterruptedException e){
                   e.printStackTrace();
               }
           }
       },"UPDATE").start();
    }
}
