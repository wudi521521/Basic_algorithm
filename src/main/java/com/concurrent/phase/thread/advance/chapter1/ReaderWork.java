package com.concurrent.phase.thread.advance.chapter1;

import java.util.Random;

/**
 * @author Dillon Wu
 * @Description: 读线程
 * @date 2021/8/23 11:48
 */
public class ReaderWork extends Thread{

    private static final Random random = new Random(System.currentTimeMillis());
    /**
     * 共享数据
     */
    private final ShareData data;

   public ReaderWork(ShareData data){
       this.data = data;
   }

    @Override
    public void run() {
       try {
           while (true){
               char[] readBuf = data.read();
               System.out.println(Thread.currentThread().getName()+" reads "+String.valueOf(readBuf.toString()));
           }
       }catch (InterruptedException e){
           e.printStackTrace();
       }
    }
}
