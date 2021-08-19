package com.thread.basic.simple.chapter6;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/19 10:52
 */
public class ProduceConsumeVersion1 {

    private int i=1;

    final private Object LOCK = new Object();

    private void produce(){
        synchronized (LOCK){
            System.out.println("produce==:"+i++);
        }
    }

    private void consume(){
        synchronized (LOCK){
            System.out.println("consume==:"+i);
        }
    }

    public static void main(String[] args) {
        ProduceConsumeVersion1 version1 = new ProduceConsumeVersion1();
        new Thread(()->{
            while (true)
            version1.produce();
        }).start();
        new Thread(()->{
            while (true)
            version1.consume();
        }).start();
    }
}
