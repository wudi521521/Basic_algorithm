package com.concurrent.phase.thread.three.automic.integer;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/27 9:50
 */
public class JITTest {
    private volatile static boolean innit = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(){
            @Override
            public void run() {
                while(!innit){
                    System.out.println(".");
                }
                /**
                 * while(true)
                 * while(!init)
                 */
            }
        }.start();
        Thread.sleep(1_000);
        new Thread(){
            @Override
            public void run() {
                innit = true;
                System.out.println("Set init to true ");
            }
        }.start();
    }
}
