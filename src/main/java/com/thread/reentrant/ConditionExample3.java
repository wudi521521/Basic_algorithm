package com.thread.reentrant;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/30 17:54
 */
public class ConditionExample3 {

    private final static ReentrantLock lock = new ReentrantLock();

    /**
     * 生产者锁
     */
    private final static Condition producerCond = lock.newCondition();

    /**
     * 消费者锁
     */
    private final static Condition consumeCond = lock.newCondition();

    /**
     * 数据存储
     */
    private final static LinkedList<Long> TIMESTAMP_POOL = new LinkedList<>();

    private final static int MAX_CAPACITY = 100;

    public static void main(String[] args) {
        IntStream.rangeClosed(0,5).forEach(ConditionExample3::beginProduce);
        IntStream.rangeClosed(0,13).forEach(ConditionExample3::beginConsume);
        for (;;){
            time();
            System.out.println("==============");
            System.out.println(lock.getWaitQueueLength(producerCond));
        }
    }

    private static void beginProduce(int i){
        new Thread(()->{
            for (;;){
                produce();
                time();
            }
        },"P-"+i).start();
    }

    private static void beginConsume(int i){
        new Thread(()->{
            for (;;){
                produce();
                time();
            }
        },"C-"+i).start();
    }

    private static void produce() {
        try {
            //获取锁
            lock.lock();
            while (TIMESTAMP_POOL.size() >= MAX_CAPACITY) {
                //生产者休眠
                producerCond.await();
            }
            long value = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "-P-" + value);
            TIMESTAMP_POOL.addLast(value);
            //消费者唤醒
            consumeCond.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void consume() {
        try {
            lock.lock();
            while (TIMESTAMP_POOL.isEmpty()) {
                //休眠
                consumeCond.await();
            }
            Long first = TIMESTAMP_POOL.removeFirst();
            System.out.println(Thread.currentThread().getName() + "-C-" + first);
            //唤醒
            producerCond.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void time(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
