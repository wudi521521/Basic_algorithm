package com.concurrent.phase.thread.basic.chapter7;

import java.util.Collection;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/19 16:23
 */
public interface Lock {

    class TimeOutException extends Exception{
        public TimeOutException(String message){
            super(message);
        }
    }

    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException ,TimeOutException;

    void unlock();

    Collection<Thread> getBlockedThread();

    int getBlocksSize();



}
