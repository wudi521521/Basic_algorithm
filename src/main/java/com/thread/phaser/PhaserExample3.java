package com.thread.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/31 13:23
 */
public class PhaserExample3 {

    public static void main(String[] args) {
        final Phaser phaser = new Phaser(5);
        for (int i = 1; i < 6; i++) {
            new PhaserExample2.Athletes(i, phaser);
        }
        new InjuredAthletes(5,phaser).start();
    }

    static class InjuredAthletes extends Thread{
        private final int no;

        private final Phaser phaser;

        InjuredAthletes(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {

            try {
                sport(phaser, no + ":start running", no + " : end running");
                sport(phaser, no + ":start bicycle", no + " : end bicycle");
                //sport(phaser, no + ":start jump", no + " : end jump");
                System.out.println(no+" oh shit,I am injured,I will be exited");
                phaser.arriveAndAwaitAdvance();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Athletes extends Thread {
        private final int no;

        private final Phaser phaser;

        Athletes(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
            start();
        }

        @Override
        public void run() {

            try {
                sport(phaser, no + ":start running", no + " : end running");
                sport(phaser, no + ":start bicycle", no + " : end bicycle");
                sport(phaser, no + ":start jump", no + " : end jump");
                //System.out.println("oh shit,I am injured");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void sport(Phaser phaser, String x, String x2) throws InterruptedException {
        System.out.println(x);
        TimeUnit.SECONDS.sleep(5);
        System.out.println(x2);
        phaser.arriveAndAwaitAdvance();
    }

}
