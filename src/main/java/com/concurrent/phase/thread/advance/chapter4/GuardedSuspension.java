package com.concurrent.phase.thread.advance.chapter4;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/23 16:29
 */
public class GuardedSuspension {
    public static void main(String[] args) throws InterruptedException{
        final RequestQueue requestQueue = new RequestQueue();
        //客户端发送
        new ClientThread(requestQueue, "Dillon").start();
        //服务端接收队列
        ServerThread serverThread = new ServerThread(requestQueue);
        serverThread.start();
        //serverThread.join();
        Thread.sleep(10_000);
        serverThread.close();

    }
}
