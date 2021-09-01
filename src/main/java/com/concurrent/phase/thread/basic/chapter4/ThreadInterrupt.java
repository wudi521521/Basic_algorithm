package com.concurrent.phase.thread.basic.chapter4;

/**
 * @author Dillon Wu
 * @Description: interrupt
 * @date 2021/8/18 16:04
 */
public class ThreadInterrupt {

    private final static   Object MONITOR = new Object();
    public static void main(String[] args) throws InterruptedException {
        /*Thread t = new Thread() {
            @Override
            public void run() {

                while (true) {
                    synchronized (MONITOR) {
                        try {
                            //Thread.sleep(10);
                            MONITOR.wait(10);
                        } catch (InterruptedException e) {
                            System.out.println("收到打断信号");
                            e.printStackTrace();
                        }
                    }
                }


            }
        };*/
        Thread t = new Thread(){
            @Override
            public void run() {
                while(true){}
            }
        };
        t.start();
        /*Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(100);
                t.interrupt();
                System.out.println("interrupt");
            } catch (Exception e) {
                e.printStackTrace();
            }

        });*/
        Thread main  = Thread.currentThread();
        Thread t2 = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                t.interrupt();
                System.out.println("interrupt");
            }
        };
        t2.start();

        try{
            t.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
