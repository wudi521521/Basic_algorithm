package com.concurrent.phase.thread.three.automic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/28 10:00
 */
public class AtomicIntegerArrayTest {

    public static void main(String[] args) {
        AtomicIntegerArray array = new AtomicIntegerArray(10);
        array.set(1,19);
        System.out.println(array.get(1));
    }
}
