package com.thread.stampe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;

/**
 * @author Dillon Wu
 * @Description: 乐观读锁
 * @date 2021/8/31 9:42
 */
public class StampedLockExample2 {

    private final static StampedLock lock = new StampedLock();

    private final static List<Long> DATA = new ArrayList<>();

    /**
     * ReentrantLock VS Synchronized
     *
     * @param args ReentrantReadWriteLock
     *             100 threads
     *             99 threads need read lock
     *             1 threads need write lock
     */
    public static void main(String[] args) {
    final ExecutorService executor = Executors.newFixedThreadPool(10);
    Runnable read = ()->{
        for (;;){
            read();
        }
    };

        Runnable write = ()->{
            for (;;){
                write();
            }
        };
        executor.submit(read);
        executor.submit(read);
        executor.submit(read);
        executor.submit(read);
        executor.submit(read);
        executor.submit(read);
        executor.submit(read);
        executor.submit(read);
        executor.submit(write);
    }


    private static void read() {
        //获取乐观锁
        long stamped = lock.tryOptimisticRead();
        if (lock.validate(stamped)){
            try {
                stamped = lock.readLock();
                Optional.of(DATA.stream()
                        .map(String::valueOf).collect(Collectors.joining("#", "R-", "")))
                        .ifPresent(System.out::println);
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlockRead(stamped);
            }
        }
    }

    private static void write(){
        long stamped = -1;
        try{
            stamped=lock.writeLock();
            System.out.println("write of value "+stamped);
            DATA.add(System.currentTimeMillis());
        }finally {
            lock.unlockWrite(stamped);
        }
    }


}
