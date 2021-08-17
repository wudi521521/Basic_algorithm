package com.thread.basic.simple.chapter2;

import com.basic.eight.Map;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/17 20:37
 */
public class ConcurrentRunnable implements Runnable {

    private int index = 0;
    private final static int Max = 50;

    @Override
    public void run() {
        while (index <= Max) {
            System.out.println(Thread.currentThread()+"的号码是:"+(index++));
        }
    }

    public static void main(String[] args) {
        final ConcurrentRunnable concurrentRunnable = new ConcurrentRunnable();
        Thread concurrent1 = new Thread(concurrentRunnable,"一号窗口");
        Thread concurrent2 = new Thread(concurrentRunnable,"二号窗口");
        Thread concurrent3 = new Thread(concurrentRunnable,"三号窗口");
        concurrent1.start();
        concurrent2.start();
        concurrent3.start();

    }
}
