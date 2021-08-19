package com.thread.basic.simple.chapter5;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/18 21:35
 */
public class BankVersion3 {

    public static void main(String[] args) {
        final SynchronizeRunnable concurrentRunnable = new SynchronizeRunnable();
        Thread concurrent1 = new Thread(concurrentRunnable,"一号窗口");
        Thread concurrent2 = new Thread(concurrentRunnable,"二号窗口");
        Thread concurrent3 = new Thread(concurrentRunnable,"三号窗口");
        concurrent1.start();
        concurrent2.start();
        concurrent3.start();

    }
}
