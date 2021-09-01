package com.concurrent.phase.thread.basic.chapter5;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/18 20:59
 */
public class TicketWindowRunnable implements Runnable {

    private int index = 1;

    private final static int Max = 500;

    private final Object MONITOR = new Object();

    @Override
    public void run() {
        while (true) {
            //同步代码块
            //1:
            synchronized (MONITOR){
                if (index > Max) {
                    break;
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "的号码是:" + (index++));
            }
            //2:
        }
    }
}
