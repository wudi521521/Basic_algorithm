package com.thread.three.automic.unsafe;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/28 12:32
 */
public class A {

    private int i=0;

    public A(){
        this.i=1;
    }

    public int get(){
        return this.i;
    }
}
