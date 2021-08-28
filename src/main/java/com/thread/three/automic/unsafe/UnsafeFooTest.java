package com.thread.three.automic.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/28 11:59
 */
public class UnsafeFooTest {

    private  static Unsafe getUnsafe(){
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    static class Guard{
        private int ACCESS_ALLOWED =1;

        private boolean allow(){
            return 42==ACCESS_ALLOWED;
        }

        public void work(){
            if (allow()){
                System.out.println("I am working ");
            }
        }
    }

    static class Simple{
        private long l=0;
        public Simple(){
            System.out.println("====");
            this.l=1;
        }

        public long get(){
            return this.l;
        }
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchFieldException {
        Simple simple = new Simple();
        System.out.println(simple.get());
        //通过反射获取
        Simple simple1= Simple.class.newInstance();
        //通过反射
        Class.forName("com.thread.three.automic.unsafe.UnsafeFooTest$Simple");
        //通过unsafe
        Unsafe unsafe = getUnsafe();
        Simple simple2 = (Simple)unsafe.allocateInstance(Simple.class);
        System.out.println(simple.get());
        System.out.println(simple.getClass());
        System.out.println(simple2.l=2);
        System.out.println(simple2.get());


        System.out.println("-----------------------------------");
        Guard guard = new Guard();
        guard.work();;
        //使用unsafe进行修改
        Field f = guard.getClass().getDeclaredField("ACCESS_ALLOWED");
        unsafe.putInt(guard,unsafe.objectFieldOffset(f),42);
        guard.work();
    }
}
