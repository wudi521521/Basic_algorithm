package com.concurrent.phase.thread.advance.chapter9;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/24 13:21
 */
public class MessageHandler {

    public void request(Message message) {
        new Thread(()->{
            String value = message.getValue();
            try{
                Thread.sleep(10);
                System.out.println("The message will be handle by"+Thread.currentThread().getName());
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }).start();
    }
}
