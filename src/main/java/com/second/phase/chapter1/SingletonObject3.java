package com.second.phase.chapter1;

/**
 * @author Dillon Wu
 * @Description:懒汉式
 * @date 2021/8/22 9:42
 */
public class SingletonObject3 {

    private static SingletonObject3 instance;

    private SingletonObject3(){
        //empty
    }

    /**
     * 串行化---影响性能
     * @return
     */
    public synchronized static SingletonObject3 getInstance(){
        if (null == instance){
            instance = new SingletonObject3();
        }
        return instance;
    }
}
