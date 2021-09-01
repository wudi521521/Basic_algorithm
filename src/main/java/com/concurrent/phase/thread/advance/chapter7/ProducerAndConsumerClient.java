package com.concurrent.phase.thread.advance.chapter7;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/24 10:33
 */
public class ProducerAndConsumerClient {

    public static void main(String[] args) {
        final MessageQueue messageQueue = new MessageQueue();
        new ProducerThread(messageQueue,1).start();
        new ProducerThread(messageQueue,2).start();
        new ProducerThread(messageQueue,3).start();

        new ConsumerThread(messageQueue,1).start();
    }
}
