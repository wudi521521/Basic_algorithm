package com.thread.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/31 11:47
 */
public class PhaserExample2 {

    /**
     * running
     * bicycle
     * long jump
     *
     * @param args
     */
    public static void main(String[] args) {
        final Phaser phaser = new Phaser(5);
        for (int i = 1; i < 6; i++) {
            new Athletes(i, phaser);
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
                System.out.println(no + "start running");
                TimeUnit.SECONDS.sleep(5);
                System.out.println(no + ": end running");
                phaser.arriveAndAwaitAdvance();

                System.out.println(no + "start bicycle");
                TimeUnit.SECONDS.sleep(5);
                System.out.println(no + ": end bicycle");
                phaser.arriveAndAwaitAdvance();

                System.out.println(no + "start jump");
                TimeUnit.SECONDS.sleep(5);
                System.out.println(no + ": end jump ");
                phaser.arriveAndAwaitAdvance();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
