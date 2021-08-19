package com.thread.basic.simple.chapter5;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/18 21:33
 */
public class SynchronizeRunnable implements Runnable {
    private int index = 1;

    private final static int Max = 500;

    private final Object MONITOR = new Object();

    //同步方法的锁是this
    @Override
    public void run() {
        while (true) {
            if (ticket())
                break;
        }

    }

    //this 锁
    private synchronized boolean ticket() {
        //同步代码块
        if (index > Max) {
            return true;
        }
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //index ++ =>index = index+1
        //1:get field index
        //2:index == index +1
        //3:put field index
        System.out.println(Thread.currentThread() + "的号码是:" + (index++));
        return false;
    }
}
