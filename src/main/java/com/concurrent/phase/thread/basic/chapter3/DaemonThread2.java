package com.concurrent.phase.thread.basic.chapter3;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/18 13:58
 */
public class DaemonThread2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            Thread innerThread = new Thread(() -> {
                try {
                    Thread.sleep(10_000);
                    System.out.println("inner_thread");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            innerThread.setDaemon(true);
            innerThread.start();
            System.out.println("inner_thread daemon："+innerThread.isDaemon());
            try {
                Thread.sleep(1_000);
                System.out.println("T thread finish done");
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        t.start();
        System.out.println(t.isDaemon());
    }
}
