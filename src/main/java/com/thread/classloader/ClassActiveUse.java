package com.thread.classloader;

import java.util.Random;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/25 21:49
 */
public class ClassActiveUse {

    public static void main(String[] args) {
        //1:new 直接使用        类的主动使用
        //    new Obj();
       // System.out.println(I.a);
       // System.out.println(Obj.salary);
        /*try {
            Class.forName("com.thread.classloader.Obj");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        //TODO 通过子类访问父类static成员变量，会初始化类
        //System.out.println(Child.age);
        //TODO 定义引用数组,不会初始化类
        Obj[] arrays = new Obj[10];//不会初始化
        System.out.println(Obj.x);
    }
}
class Obj{
    //todo final修饰的常量会在编译期间放到常量池中,不会初始化类
    public static  final long salary =100_000;
    //TODO final修饰的复杂类型,在编译期间无法计算得出，会初始化类
    public static  final int x = new Random().nextInt(100);
    static {
        System.out.println("Obj 被初始化");
    }

    public static void  print(){
        System.out.println("==");
    }

}
//访问某个类或者接口的静态变量，或者对该静态变量进行赋值操作
//1:对某个类的静态变量来进行读写-》class
//2:对接口中静态变量进行读取    -》interface
interface I{
     int a=10;
}

class Child extends Obj{
    public static int age=31;
    static {
        System.out.println("Child 被初始化");
    }
}
