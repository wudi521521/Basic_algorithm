package com.concurrent.phase.thread.advance.chapter3;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/23 14:30
 */
public class AsynFuture<T> implements Future<T> {

    private volatile  boolean done = false;

    private T result;

    /**
     * 完成结果
     * @param result
     */
    public void done(T result){
        synchronized (this){
            this.result = result;
            this.done = true;
            this.notifyAll();
        }
    }

    @Override
    public T get() throws InterruptedException {

        synchronized (this){
            System.out.println("asyn invoker wait");
            while (!done){
                this.wait();
                System.out.println("asyn invoker notify1");
            }
            System.out.println("asyn invoker notify2");

        }
        System.out.println("asyn invoker notify3");

        return result;
    }
}
