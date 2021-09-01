package com.concurrent.phase.thread.advance.chapter1;

/**
 * @author Dillon Wu
 * @Description: 读写锁
 * @date 2021/8/23 11:10
 */
public class ReadWriteLock {

    private int readingReaders = 0;

    private int waitingReaders =0;

    private int writingWriters =0;

    private int waitingWriters =0;

    private boolean preferWriters = true;

    public ReadWriteLock(){
        this(true);
    }

    public ReadWriteLock(boolean preferWriters){
        this.preferWriters = preferWriters;
    }


    /**
     * 获取读锁
     * @throws InterruptedException
     */
    public synchronized void readLock() throws InterruptedException{
        //准备读
        this.waitingReaders++;
        try {
            //是否有线程在写{有线程在写就需要阻塞等待}
            while(writingWriters>0 || (preferWriters && writingWriters>0)){
                this.wait();
            }
            //正在读
            this.readingReaders++;
        }catch (InterruptedException e){

        }finally {
            //已经读完
            this.waitingReaders--;
        }

    }

    /**
     * 放弃读锁
     */
    public synchronized void readUnlock(){
        this.readingReaders--;
        this.notifyAll();
    }

    /**
     * 获取写锁
     */
    public synchronized void writeLock() throws InterruptedException{
        //准备写锁
        this.waitingWriters++;
        try {
           while(readingReaders>0 || writingWriters>0){
               this.wait();
           }
           //写锁进行中
           this.writingWriters++;
        }finally {
            //准备写锁完成
            this.waitingWriters--;
        }
    }

    /**
     * 释放写锁
     */
    public synchronized void writeUnlock(){
        this.writingWriters--;
        this.notifyAll();
    }

}
