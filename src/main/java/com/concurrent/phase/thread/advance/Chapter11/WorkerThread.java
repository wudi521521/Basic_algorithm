package com.concurrent.phase.thread.advance.Chapter11;

import java.util.Random;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/25 9:44
 */
public class WorkerThread extends Thread {

    private final Channel channel;

    private static final Random random = new Random(System.currentTimeMillis());


    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true) {
            channel.take().executor();
            try{
                Thread.sleep(random.nextInt(1_000));

            }catch (InterruptedException e){
            }
        }
    }
}
