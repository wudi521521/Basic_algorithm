package com.thread.advance.chapter1;

/**
 * @author Dillon Wu
 * @Description: ReadWriteLock design pattern
 *               Reader-Writer design pattern
 * @date 2021/8/23 12:38
 */
public class ReadWriteLockClient {
    public static void main(String[] args) {
        final ShareData shareData = new ShareData(10);
        new ReaderWork(shareData).start();
        new ReaderWork(shareData).start();
        new ReaderWork(shareData).start();
        new ReaderWork(shareData).start();
        new ReaderWork(shareData).start();
        new WriteWork(shareData,"sdfdfdfdf").start();
        new WriteWork(shareData,"sdjflksdfj").start();
    }
}
