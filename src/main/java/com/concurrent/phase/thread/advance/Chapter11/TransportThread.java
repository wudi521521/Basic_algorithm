package com.concurrent.phase.thread.advance.Chapter11;

import java.util.Random;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/25 10:03
 */
public class TransportThread extends Thread {

    private final Channel channel;

    private static final Random random = new Random(System.currentTimeMillis());

    public TransportThread(String name,Channel channel){
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        super.run();
        try{
            for(int i=0;true;i++){
                Request request = new Request(getName(),i);
                this.channel.put(request);
                Thread.sleep(random.nextInt(1_000));
            }
        }catch (Exception e){}
    }
}
