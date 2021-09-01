package com.concurrent.phase.thread.advance.chapter7;


import java.util.LinkedList;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/24 10:09
 */
public class MessageQueue {

    private final LinkedList<Message> queue;

    private final static int DEFAULT_MAX_LIMIT=10;

    private final int limit;

    public MessageQueue(){
        this(DEFAULT_MAX_LIMIT);
    }

    public MessageQueue(final int limit){
        this.limit = limit;
        this.queue = new LinkedList<>();
    }

    public void put(Message message) throws InterruptedException{
        synchronized (queue){
            while (queue.size()>limit){
                queue.wait();
            }
            queue.addLast(message);
        }
    }

    public Message take() throws InterruptedException{
        synchronized (queue){
            while (queue.isEmpty()){
                System.out.println("========take========");
                queue.wait();
            }
            Message message = queue.removeFirst();
            queue.notifyAll();
            return message;
        }
    }

    public int getMaxLimit(){
        return this.limit;
    }

    public int getMessageSize() throws InterruptedException{
        synchronized (queue){
            return queue.size();
        }
    }
}
