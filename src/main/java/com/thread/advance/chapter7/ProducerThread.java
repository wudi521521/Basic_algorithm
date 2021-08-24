package com.thread.advance.chapter7;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/24 10:23
 */
public class ProducerThread extends Thread {

    private final MessageQueue messageQueue;

    private final static Random random = new Random(System.currentTimeMillis());

    private final static AtomicInteger counter = new AtomicInteger(0);

    public ProducerThread(MessageQueue messageQueue, int seq) {
        super("PRODUCER");
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message message = new Message("message-" + counter.getAndIncrement());
                messageQueue.put(message);
                System.out.println(Thread.currentThread().getName()+" put message "+message.getData());
                 Thread.sleep(1_000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
