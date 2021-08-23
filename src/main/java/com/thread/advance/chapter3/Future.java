package com.thread.advance.chapter3;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/23 14:27
 */
public interface Future<T> {

    T get() throws InterruptedException;


}
