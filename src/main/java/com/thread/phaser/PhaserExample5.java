package com.thread.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/31 14:22
 */
public class PhaserExample5 {

    public static void main(String[] args) {
        /*final Phaser phaser = new Phaser(0);
        //到达并等待唤醒，等到为0时就唤醒
        new Thread(phaser::arriveAndAwaitAdvance).start();*/

        final  Phaser phaser = new Phaser(5);
        for (int i=0;i<4;i++){
            new ArriveTask(phaser,i).start();
        }
        phaser.arriveAndAwaitAdvance();
        System.out.println("The phaser 1 worker");
    }

    private static class ArriveTask extends Thread{
        private final Phaser phaser;

        private ArriveTask(Phaser phaser,int no){
            super(String.valueOf(no));
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(getName() + " start working");
             sleepSeconds(2);
            System.out.println(getName()+" The phase one is running");
            phaser.arrive();
            sleepSeconds(2);

            System.out.println(getName()+"keep to do other thing");

        }
    }

    private static void sleepSeconds(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
