package com.concurrent.phase.thread.advance.Chapter11;

import java.util.Arrays;
import java.util.List;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/25 9:41
 */
public class Channel {
    private final static int MAX_REQUEST = 100;

    private final Request[] requestQueue;

    private int head;

    private int tail;

    private int count;

    private final WorkerThread[] workerPool;

    public Channel(int workers){
       this.requestQueue = new Request[MAX_REQUEST];
       this.head=0;
       this.tail=0;
       this.count=0;
       this.workerPool = new WorkerThread[workers];
       this.init();
    }

    /**
     * 初始化
     */
    private void init(){
        for (int i=0;i<workerPool.length;i++){
            workerPool[i] = new WorkerThread("Worker-"+i,this);
        }
    }

    /**
     * 工人开始工作
     */
    public void startWorker(){
        List<WorkerThread> workerThreads = Arrays.asList(workerPool);//.forEach(worker->worker.start());
        workerThreads.stream().forEach(workerThread -> workerThread.start());
    }

    public synchronized void put(Request request){
        //如果队列中的数据满了，就停止一下
        while (count>=requestQueue.length){
            try{
                this.wait();
            }catch (Exception e){

            }
        }
        this.requestQueue[tail]=request;
        this.tail = (tail+1)%requestQueue.length;
        if (tail>=requestQueue.length){
            tail=0;
        }
        this.count++;
        this.notifyAll();
    }

    public synchronized Request take(){
        while (count<=0){
             try{
                this.wait();
            }catch (Exception e){

            }
        }
        Request request = this.requestQueue[head];
        this.head = (this.head+1)%this.requestQueue.length;
        this.count--;
        this.notifyAll();
        return request;

    }
}
