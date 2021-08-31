package com.thread.phaser;

import java.util.concurrent.Phaser;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/31 14:36
 */
public class PhaserExample6 {
    //awaitAdvance
    public static void main(String[] args) {
        final Phaser phaser = new Phaser(6);
        new Thread(()->phaser.arrive()).start();
        System.out.println(phaser.getArrivedParties());

    }

    private static class AwaitAdvanceTask extends Thread{
        private final Phaser phaser;

        private AwaitAdvanceTask(Phaser phaser){
            this.phaser = phaser;
        }

        @Override
        public void run() {
            super.run();
        }
    }
}
