package com.second.phase.chapter1;

/**
 * @author Dillon Wu
 * @Description:懒汉式
 * @date 2021/8/22 9:42
 */
public class SingletonObject5 {

    //volatile 不能保证原子性，但能保证内存可见性，有序性
    private static volatile SingletonObject5 instance;

    private SingletonObject5(){
        //empty
    }

    /**
     *double check 双重加载 add volatile
     * @return
     */
    public static SingletonObject5 getInstance(){
        if (null == instance){
            synchronized (SingletonObject5.class){
                if (null == instance){
                    instance = new SingletonObject5();
                }
            }
        }
        return instance;
    }
}
