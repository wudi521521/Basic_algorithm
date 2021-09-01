package com.concurrent.phase.thread.advance.chapter8;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/24 11:18
 */
public class Test {
    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        final CountDown latch = new CountDown(5);
        System.out.println("准备多线程处理");
        //The first phase
        IntStream.rangeClosed(1,5).forEach(i->{
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" is working");
                try{
                    Thread.sleep(random.nextInt(1000));
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                //标记本线程完成
                latch.down();
            },String.valueOf(i)).start();
        });
        latch.await();
        //The second phase
        System.out.println("多线程任务全部结束，准备第二阶段任务");
        System.out.println("-----------------");
        System.out.println("FINISH");
    }
}
