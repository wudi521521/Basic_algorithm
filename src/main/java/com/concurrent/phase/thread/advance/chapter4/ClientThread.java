package com.concurrent.phase.thread.advance.chapter4;

import java.util.Random;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/23 16:16
 */
public class ClientThread extends Thread {

    private final RequestQueue queue;

    private final Random random;

    private final String sendValue;

    public ClientThread(RequestQueue queue,String sendValue){
        this.queue = queue;
        this.sendValue = sendValue;
        random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++){
            System.out.println("Client-> request"+sendValue);
            //数据放入队列中
            queue.putRequest(new Request(sendValue));
            try{
                Thread.sleep(random.nextInt(1000));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
