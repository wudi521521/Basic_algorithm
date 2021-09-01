package com.concurrent.phase.thread.advance.chapter10;

import java.util.Random;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/24 13:43
 */
public class CounterIncrement extends Thread {

    private volatile boolean terminated = false;

    private int counter = 0;

    private Random random = new Random(System.currentTimeMillis());

    @Override
    public void run() {
        try {
            while (!terminated) {
                System.out.println(Thread.currentThread().getName() + " " + counter++);
                Thread.sleep(random.nextInt(1_000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.clean();
        }
    }
    private void clean(){
        System.out.println("do some clean work for the second phase");
    }

    public void close() {
        this.terminated = true;
        this.interrupt();
    }
}
