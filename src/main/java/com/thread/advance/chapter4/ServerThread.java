package com.thread.advance.chapter4;

import java.util.Random;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/23 16:21
 */
public class ServerThread extends Thread {
    private final RequestQueue queue;

    private final Random random;

    private volatile boolean flag = false;

    public ServerThread(RequestQueue queue){
        this.queue = queue;
        random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        while (!flag){
            //队列中取值
            Request request = queue.getRequest();
            if (null == request){
                System.out.println("Received the empty request");
                continue;
            }
            System.out.println("server -->"+request.getValue());
            try{
                Thread.sleep(random.nextInt(1000));
            }catch (InterruptedException e){
                e.printStackTrace();
                return;
            }
        }
    }

    public void close(){
        System.out.println("Server Close");
        this.flag = true;
        this.interrupt();
    }
}
