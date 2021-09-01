package com.concurrent.phase.chapter1;

/**
 * @author Dillon Wu
 * @Description:懒汉式
 * @date 2021/8/22 9:42
 */
public class SingletonObject2 {

    private static SingletonObject2 instance;

    private SingletonObject2(){
        //empty
    }

    /**
     * 线程不安全
     * @return
     */
    public static SingletonObject2 getInstance(){
        if (null == instance){
            instance = new SingletonObject2();
        }
        return instance;
    }
}
