package com.concurrent.phase.thread.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/31 11:38
 */
public class PhaserExample1 {

    public static void main(String[] args) {
        final Phaser phaser = new Phaser();
        IntStream.rangeClosed(1,5).boxed().map(i->phaser).forEach(Task::new );
        //动态控制phaser的个数
        phaser.register();
        phaser.register();
        //取消注册
        phaser.arriveAndAwaitAdvance();
        System.out.println("all of worker finished the task");

    }

    static class Task extends Thread{
        private final Phaser phaser;

        Task(Phaser phaser){
            this.phaser = phaser;
            this.phaser.register();
            start();
        }

        @Override
        public void run() {
            System.out.println("The Worker ["+getName()+"] is working");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
