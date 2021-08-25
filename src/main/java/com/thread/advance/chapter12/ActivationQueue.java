package com.thread.advance.chapter12;

import java.util.LinkedList;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/25 13:29
 */
public class ActivationQueue {

    private final static int MAX_METHOD_REQUEST_QUEUE_SIZE = 100;

    private final LinkedList<MethodRequest> methodQueue;

    public ActivationQueue() {
        methodQueue = new LinkedList<>();
    }

    public synchronized void put(MethodRequest request) {
        while (methodQueue.size() >= MAX_METHOD_REQUEST_QUEUE_SIZE){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.methodQueue.addLast(request);
        this.notifyAll();
    }

    public synchronized MethodRequest take(){
        while (methodQueue.isEmpty()){
            try{
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        MethodRequest methodRequest = methodQueue.removeFirst();
        this.notifyAll();
        return methodRequest;
    }

}
