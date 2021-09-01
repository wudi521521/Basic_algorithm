package com.concurrent.phase.thread.basic.chapter4;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/18 15:38
 */
public class ThreadJoin2 {
    public static void main(String[] args) throws InterruptedException{
        Thread t = new Thread(()->{
            System.out.println("t1 is running");
            try{
                Thread.sleep(1_000);
            }catch (Exception e){
                e.printStackTrace();
            }

        });
        t.start();
        t.join(1_000);


        Optional.of("All of tasks finish done").ifPresent(System.out::println);
        IntStream.range(1,1000).
                forEach(i-> System.out.println(Thread.currentThread().getName()+"->"+i));

    }
}
