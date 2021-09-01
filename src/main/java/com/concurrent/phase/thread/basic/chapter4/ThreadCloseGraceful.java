package com.concurrent.phase.thread.basic.chapter4;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/18 17:13
 */
public class ThreadCloseGraceful {

    private static class Worker extends Thread{
        private volatile  boolean start = true;

        @Override
        public void run() {
            while (start){

            }
        }

        public void shutdown(){
            this.start=false;
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();
        try{
            Thread.sleep(10_000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        worker.shutdown();
    }
}
