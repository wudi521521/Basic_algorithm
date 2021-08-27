package com.thread.three.automic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/27 13:40
 */
public class AtomicBooleanTest {

    public static void main(String[] args) {
        AtomicBoolean bool = new AtomicBoolean(true);
        System.out.println(bool.get());
        System.out.println(bool.getAndSet(false));
        System.out.println(bool.compareAndSet(true,false));
    }
}
