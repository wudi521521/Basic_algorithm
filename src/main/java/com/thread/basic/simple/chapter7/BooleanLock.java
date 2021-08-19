package com.thread.basic.simple.chapter7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/19 16:28
 */
public class BooleanLock implements Lock {
    //The initValue is true indicated the lock have be get;
    //The innitValue is false indicated the lock is free (other thread can get this)
    private volatile boolean initValue;

    private Collection<Thread> blockedThreadCollection = new ArrayList<>();

    private Thread currentThread;

    public BooleanLock() {
        initValue = false;
    }

    //线程安全===同步方法
    @Override
    public synchronized void lock() throws InterruptedException {
        System.out.println("========lock======" + Thread.currentThread().getName());
        //安全定义锁
        while (initValue) {
            blockedThreadCollection.add(Thread.currentThread());
            this.wait();
        }
        blockedThreadCollection.remove(Thread.currentThread());
        this.initValue = true;
        //定义当前锁
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized  void lock(long mills) throws InterruptedException, TimeOutException {
        if (mills<=0){
            lock();
        }
        //有效时间内获取不到锁,
        long hasRemaining = mills;
        long endTime = System.currentTimeMillis()+mills;
        while (initValue){
            if (hasRemaining<=0){
                throw new TimeOutException("Tume=========");
            }
            blockedThreadCollection.add(Thread.currentThread());
            this.wait(mills);
            hasRemaining = System.currentTimeMillis()-endTime;
        }
        this.initValue = true;
    }

    @Override
    public synchronized void unlock() {
        //获取当前锁并进行判断
        if (currentThread==Thread.currentThread()){
            //安全解锁
            this.initValue = false;
            System.out.println(Thread.currentThread().getName() + " release the lock monitor");
            this.notifyAll();
        }

    }

    @Override
    public Collection<Thread> getBlockedThread() {
        //查询的时候不能修改此数据,如果修改就报错
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlocksSize() {
        return 0;
    }
}
