package com.concurrent.phase.chapter1;

import java.util.stream.IntStream;

/**
 * @author Dillon Wu
 * @Description:枚举单例
 * @date 2021/8/22 9:42
 */
public class SingletonObject7 {

    private SingletonObject7(){
        //empty
    }
    /**
     * 枚举是线程安全的，单例
     */
    private enum Singleton{
        INSTANCE;
        private final SingletonObject7 instance;
        Singleton(){
            System.out.println("实例创建==");
            instance = new SingletonObject7();
        }

        public SingletonObject7 getInstance() {
            return instance;
        }
    }

    /**
     *double check 双重加载 add volatile
     * @return
     */
    public static SingletonObject7 getInstance(){
       return Singleton.INSTANCE.getInstance();
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(1,100).forEach(item->new Thread(String.valueOf(item)){
            @Override
            public void run() {
                System.out.println(SingletonObject7.getInstance());
            }
        }.start());
    }
}
