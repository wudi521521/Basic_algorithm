package com.second.phase.chapter1;

/**
 * @author Dillon Wu
 * @Description:懒汉式
 * @date 2021/8/22 9:42
 */
public class SingletonObject4 {

    private static SingletonObject4 instance;

    private SingletonObject4(){
        //empty
    }

    /**
     *double check 双重加载 问题:可能会造成空指针异常
     * @return
     */
    public static SingletonObject4 getInstance(){
        if (null == instance){
            synchronized (SingletonObject4.class){
                if (null == instance){
                    instance = new SingletonObject4();
                }
            }
        }
        return instance;
    }
}
