package com.thread.basic.simple.chapter5;

import com.thread.basic.simple.chapter2.ConcurrentRunnable;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/18 21:02
 */
public class BankVersion2 {

    public static void main(String[] args) {
        final TicketWindowRunnable concurrentRunnable = new TicketWindowRunnable();
        Thread concurrent1 = new Thread(concurrentRunnable,"一号窗口");
        Thread concurrent2 = new Thread(concurrentRunnable,"二号窗口");
        Thread concurrent3 = new Thread(concurrentRunnable,"三号窗口");
        concurrent1.start();
        concurrent2.start();
        concurrent3.start();

    }
}
