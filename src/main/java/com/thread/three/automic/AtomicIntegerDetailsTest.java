package com.thread.three.automic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/27 10:00
 */
public class AtomicIntegerDetailsTest {

    public static void main(String[] args) {
        /*AtomicInteger i = new AtomicInteger();
        System.out.println(i.get());
        i = new AtomicInteger(10);
        System.out.println(i.get());
        i.set(11);
        System.out.println(i.get());
        i.lazySet(13);
        System.out.println(i.get());
        AtomicInteger a = new AtomicInteger(10);
        int andSet = a.getAndAdd(10);
        System.out.println(andSet);
        System.out.println(a.get());*/
        AtomicInteger i = new AtomicInteger(10);
        i.compareAndSet(10,20);
        System.out.println(i.get());

    }
}
