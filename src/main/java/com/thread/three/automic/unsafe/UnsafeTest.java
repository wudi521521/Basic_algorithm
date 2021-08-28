package com.thread.three.automic.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/28 10:46
 */
public class UnsafeTest {
    public static void main(String[] args) throws Exception {
        /*Unsafe unsafe = getUnsafe();
        System.out.println(unsafe);*/
        /**
         * stupidCounter
         */
        ExecutorService service = Executors.newFixedThreadPool(10000);
        Counter counter=new CASCounter();
        long start = System.currentTimeMillis();
        for (int i=0;i<1000;i++){
            service.submit(new CounterRunnable(counter,10000));
        }
        //线程只是打了interrupt
        service.shutdown();
        //线程池等待一个小时,一个小时之后线程池关闭
        service.awaitTermination(1, TimeUnit.HOURS);
        long end = System.currentTimeMillis();
        System.out.println("Counter Result:"+counter.getCounter());
        System.out.println("Counter Result:"+(end-start));

    }

    private  static Unsafe getUnsafe(){
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    interface Counter{
        void increment();
        long getCounter();
    }

    /**
     *
     */
    static class StupidCounter implements Counter{

        private long counter = 0;

        @Override
        public void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    /**
     * 同步方法测试
     */
    static class SyncStupidCounter implements Counter{

        private long counter = 0;

        @Override
        public synchronized void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    /**
     * 添加显示锁测试
     */
    static class LockCounter implements Counter{

        private long counter = 0;

        private final Lock lock = new ReentrantLock();

        @Override
        public  void increment() {
            try {
                lock.lock();
                counter++;
            }finally {
                lock.unlock();
            }

        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    /**
     * 原子测试
     */
    static class AtomicCounter implements Counter{

        private AtomicLong counter = new AtomicLong(0);

        @Override
        public void increment() {
            counter.getAndIncrement();
        }

        @Override
        public long getCounter() {
            return counter.get();
        }
    }

    /**
     * 自定义CAS算法
     */
    static class CASCounter implements Counter{

        private volatile long counter = 0;
        private Unsafe unsafe;
        private long offSet;

        CASCounter() throws Exception{
            unsafe = getUnsafe();
            //代码偏移量
            offSet=unsafe.objectFieldOffset(CASCounter.class.getDeclaredField("counter"));
        }

        @Override
        public void increment() {
            long current = counter;
            //不成功采取做
            while (!unsafe.compareAndSwapLong(this,offSet,current,current+1)){
                current=counter;
            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }


    static class CounterRunnable implements Runnable{
        private Counter counter;

        private int num;

        public CounterRunnable(Counter counter, int num) {
            this.counter = counter;
            this.num = num;
        }

        @Override
        public void run() {
            for (int i=0;i<num;i++){
                counter.increment();
            }
        }
    }
}
