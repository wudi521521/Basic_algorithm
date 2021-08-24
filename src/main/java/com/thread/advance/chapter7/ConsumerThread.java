package com.thread.advance.chapter7;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/24 10:31
 */
public class ConsumerThread extends Thread {
    private final MessageQueue messageQueue;

    private final static Random random = new Random(System.currentTimeMillis());

    public ConsumerThread(MessageQueue messageQueue, int seq) {
        super("CONSUMER");
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message message = messageQueue.take();
                System.out.println(Thread.currentThread().getName()+" get message "+message.getData());
                Thread.sleep(1_000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
