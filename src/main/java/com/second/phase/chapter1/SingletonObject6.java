package com.second.phase.chapter1;

/**
 * @author Dillon Wu
 * @Description:static 单例
 * @date 2021/8/22 9:42
 */
public class SingletonObject6 {

    private  SingletonObject6(){
        //empty
    }
    private static  class InstanceHolder{
        //static 初始化一次
        private final static  SingletonObject6 instance = new SingletonObject6();
    }
    /**
     *内部类--static--只初始化一次
     * @return
     */
    public static SingletonObject6 getInstance(){
       return InstanceHolder.instance;
    }
}
