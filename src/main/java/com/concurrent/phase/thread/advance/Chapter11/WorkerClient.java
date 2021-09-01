package com.concurrent.phase.thread.advance.Chapter11;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/25 10:14
 */
public class WorkerClient {
    public static void main(String[] args) {
        final Channel channel = new Channel(5);
        channel.startWorker();

        new TransportThread("Dillon",channel).start();
        new TransportThread("jack",channel).start();
        new TransportThread("Tom",channel).start();
    }
}
