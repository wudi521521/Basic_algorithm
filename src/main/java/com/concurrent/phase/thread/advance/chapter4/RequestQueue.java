package com.concurrent.phase.thread.advance.chapter4;


import java.util.LinkedList;

/**
 * @author Dillon Wu
 * @Description: 共享队列数据
 * @date 2021/8/23 16:03
 */
public class RequestQueue {
    private final LinkedList<Request> queue = new LinkedList<>();

    public  Request getRequest(){
        synchronized (queue){
            while (queue.size()<=0){
                try{
                    queue.wait();
                }catch (InterruptedException e){
                    System.out.println("RequestQueue exception"+e);
                    return null;
                }
            }
            Request request = queue.removeFirst();
            return request;
        }
    }

    public void putRequest(Request request){
        synchronized (queue){
            queue.addLast(request);
            queue.notifyAll();
        }
    }
}
