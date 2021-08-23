package com.thread.advance.chapter1;

/**
 * @author Dillon Wu
 * @Description: 共享数据
 * @date 2021/8/23 11:23
 */
public class ShareData {

    private final char[] buffer;

    private final ReadWriteLock lock = new ReadWriteLock();

    public ShareData(int size){
        this.buffer = new char[size];
        for (int i=0;i<size;i++){
            buffer[i]='0';
        }
    }

    /**
     * 读数据
     * @return
     * @throws InterruptedException
     */
    public char[] read() throws InterruptedException{
        try {
            //获取锁
            lock.readLock();
            return this.doRead();
        }finally {
            //释放锁
            lock.readUnlock();
        }
    }

    /**
     * 写数据
     * @throws InterruptedException
     */
    public void write (char c) throws InterruptedException{
        try {
            //获取锁
            lock.writeLock();
             this.doWrite(c);
        }finally {
            //释放锁
            lock.writeUnlock();
        }
    }

    private void doWrite(char c){
        for (int i=0;i<buffer.length;i++){
            buffer[i]=c;
            slowly(10);
        }
    }

    /**
     * 读数据
     * @return
     */
    private char[] doRead(){
        char[] newBuf = new char[buffer.length];
        for (int i=0;i<buffer.length;i++){
            newBuf[i]=buffer[i];
        }
        slowly(50);
        return newBuf;
    }

    /**
     * 睡眠
     * @param ms
     */
    private void slowly(int ms){
        try {
            Thread.sleep(ms);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
