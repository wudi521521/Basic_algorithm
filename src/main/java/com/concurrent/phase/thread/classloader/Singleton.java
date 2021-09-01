package com.concurrent.phase.thread.classloader;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/26 9:52
 */
public class Singleton {

    public static  int x=0;

    public static int y;

    private static Singleton instance =new Singleton();

    /**
     * 链接：
     * int x=0;
     * int y=0;
     * instance=null;
     * 初始化:
     * instance = new Singleton()
     * x++=>1;y++=>1
     */
    /**
     * 链接:
     *1: instance =null;
     *2: x=0;y=0;
     * 初始化:
     * instance = new Singleton();
     *  x++=>1;y++=>1;
     * 重新赋值
     * x=0;y=1;
     */
    private Singleton(){
        x++;
        y++;
    }

    public static  Singleton getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        Singleton singleton = getInstance();
        System.out.println(singleton.x);
        System.out.println(singleton.y);
    }
}
