package com.concurrent.phase.thread.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/31 14:00
 */
public class PhaserExample4 {

    public static void main(String[] args) {
        final Phaser phaser = new Phaser(1);
        /*System.out.println(phaser.getPhase());

        phaser.arriveAndAwaitAdvance();
        System.out.println(phaser.getPhase());

        phaser.arriveAndAwaitAdvance();
        System.out.println(phaser.getPhase());

        phaser.arriveAndAwaitAdvance();
        System.out.println(phaser.getPhase());*/
       /* System.out.println(phaser.getRegisteredParties());

        phaser.register();

        System.out.println(phaser.getRegisteredParties());*/

       /* System.out.println(phaser.getArrivedParties());
        System.out.println(phaser.getUnarrivedParties());

        phaser.bulkRegister(10);
        System.out.println(phaser.getRegisteredParties());
        System.out.println(phaser.getArrivedParties());
        System.out.println(phaser.getUnarrivedParties());*/

       final Phaser phaser1 = new Phaser(2){
           @Override
           protected boolean onAdvance(int phase, int registeredParties) {
               return true;
           }
       };
       new OnAdvanceTask("A",phaser1).start();
       new OnAdvanceTask("B",phaser1).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(phaser1.getUnarrivedParties());
        System.out.println(phaser1.getArrivedParties());

    }

    static class OnAdvanceTask extends Thread{

        private final Phaser phaser;

        OnAdvanceTask(String name,Phaser phaser){
            super(name);
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(getName()+"I am start and the phase"+phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
            System.out.println(getName()+"I am end and the phaser");

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (getName().equals("A")){
                System.out.println(getName()+"I am start and the phase"+phaser.getPhase());
                phaser.arriveAndAwaitAdvance();
                System.out.println(getName()+"I am end and the phaser");
            }

        }
    }
}
