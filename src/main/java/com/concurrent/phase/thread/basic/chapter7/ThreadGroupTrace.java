package com.concurrent.phase.thread.basic.chapter7;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/20 11:33
 */
public class ThreadGroupTrace {
    public static void main(String[] args) {
        //use the name

        //use the parent and group name

        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1, "t1"){
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println(getThreadGroup().getName());
                        System.out.println(getThreadGroup().getParent().getName());
                        Thread.sleep(10_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();

        ThreadGroup tg2 = new ThreadGroup(tg1,"TG2");
        System.out.println("===="+tg2.getName());
        System.out.println("===="+tg2.getParent());
    }
}
