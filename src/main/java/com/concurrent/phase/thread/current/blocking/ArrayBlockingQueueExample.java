package com.concurrent.phase.thread.current.blocking;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/3 10:56
 */
public class ArrayBlockingQueueExample {

    public static void main(String[] args) {

    }

    /**
     * 1:FIFO(First in First out)
     * 2:once created,The capacity cannot be changed
     * 3:full queue will result int the operation blocking
     * @param size
     * @param <T>
     * @return
     */
    public <T> ArrayBlockingQueue<T>  create(int size){
        return new ArrayBlockingQueue<T>(size);
    }
}
