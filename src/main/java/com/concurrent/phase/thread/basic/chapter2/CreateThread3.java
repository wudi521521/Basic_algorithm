package com.concurrent.phase.thread.basic.chapter2;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/18 11:43
 */
public class CreateThread3 {
    /**
     * stack 内存比较小，主要存储引用，基本数据
     * heap 内存比较大,主要存储实例
     */

    private int i = 0;

    private byte[] bytes = new byte[1024];

    private static int counter = 0;

    //jvm will create a thread named "main"
    public static void main(String[] args) {
        int j = 0;
        int[] arr = new int[1024];
        try {
            add(0);
        } catch (Error e) {
            e.printStackTrace();
            System.out.println(counter);
        }

    }

    private static void add(int i) {
        ++counter;
        add(i + 1);
    }
}
