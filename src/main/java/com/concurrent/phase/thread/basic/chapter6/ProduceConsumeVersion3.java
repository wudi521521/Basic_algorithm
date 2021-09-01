package com.concurrent.phase.thread.basic.chapter6;

import java.util.stream.Stream;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/19 10:52
 */
public class ProduceConsumeVersion3 {

    private int i=1;

    final private Object LOCK = new Object();

    private volatile boolean isProduces = false;



    private void produce(){
        synchronized (LOCK){
            //没有消费的时候需要等待
            while (isProduces){
                try {
                    //等待
                    LOCK.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
                //唤醒去生产数据
                i++;
                System.out.println("produce==:"+i);
                LOCK.notifyAll();
                isProduces=true;
        }
    }

    private void consume(){
        synchronized (LOCK){
            while (!isProduces){
                //没有生产时需要等待
                try {
                    //等待
                    LOCK.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
                System.out.println("consume==:"+i);
                //唤醒
                LOCK.notifyAll();
                isProduces=false;
        }
    }

    public static void main(String[] args) {
        ProduceConsumeVersion3 version2 = new ProduceConsumeVersion3();
        Stream.of("P1","p2").forEach(n->{
            new Thread(()->{
                while (true)
                    version2.produce();
            }).start();
        });
        Stream.of("C1","C2").forEach(n-> {
            new Thread(() -> {
                while (true)
                    version2.consume();
            }).start();
        });
    }
}
