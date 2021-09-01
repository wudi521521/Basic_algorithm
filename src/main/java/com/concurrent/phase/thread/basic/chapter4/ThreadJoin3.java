package com.concurrent.phase.thread.basic.chapter4;

/**
 * @author Dillon Wu
 * @Description: join 主要作用是所有的线程执行完,主线程才可以执行
 * @date 2021/8/18 15:45
 */
public class ThreadJoin3 implements Runnable {

    private String machineName;

    private long spendTime;

    public ThreadJoin3(String machineName, long spendTime) {
        this.machineName = machineName;
        this.spendTime = spendTime;
    }

    @Override
    public void run() {
        try {
              Thread.sleep(spendTime);
            System.out.println(machineName+"completed capture and successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String result() {
        return machineName + "finish";
    }


    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread t1 = new Thread(new ThreadJoin3("M1",10_000L));
        Thread t2 = new Thread(new ThreadJoin3("M2",10_000L));
        Thread t3 = new Thread(new ThreadJoin3("M3",10_000L));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        long endTime = System.currentTimeMillis();
        System.out.println(" main thread finished");

    }

}
