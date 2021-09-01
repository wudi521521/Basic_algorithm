package com.concurrent.phase.chapter1;

/**
 * @author Dillon Wu
 * @Description: 饥饿模式
 * @date 2021/8/22 9:34
 */
public class SingletonObject1 {

    /**
     * can't lazy load
     */
    private static final SingletonObject1 instance = new SingletonObject1();

    private SingletonObject1(){
        //empty
    }

    public static  SingletonObject1 getInstance(){
        return instance;
    }
}
