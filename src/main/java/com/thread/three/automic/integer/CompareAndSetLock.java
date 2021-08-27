package com.thread.three.automic.integer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/27 11:03
 */
public class CompareAndSetLock {
    private final AtomicInteger value = new AtomicInteger(0);

    private Thread lockedThread;

    public void tryLock() throws GetLockException {
        boolean success = value.compareAndSet(0, 1);
        if (!success){
            throw new GetLockException("Get the lock failed");
        }else
            lockedThread = Thread.currentThread();
    }

    public void unlock(){
        if (0==value.get()){
            return;
        }
        if (lockedThread !=null){
            value.compareAndSet(1,0);
        }
    }
}
