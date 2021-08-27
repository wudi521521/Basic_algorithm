package com.thread.classloader.jvm;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/26 13:37
 */
public class BootClassLoader {
    public static void main(String[] args) throws ClassNotFoundException{
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));

        Class<?> aClass = Class.forName("com.thread.classloader.jvm.SimpleObject");
        System.out.println(aClass.getClassLoader());
        System.out.println(aClass.getClassLoader().getParent());
    }
}
