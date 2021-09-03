package com.concurrent.phase.thread.current.blocking;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/3 13:54
 */
public class PriorityBlockingQueueExample {

    public <T> PriorityBlockingQueue<T> create(int size){
        return new PriorityBlockingQueue<>(size);
    }
}
