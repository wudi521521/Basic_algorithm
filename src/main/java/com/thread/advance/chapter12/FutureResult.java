package com.thread.advance.chapter12;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/25 11:46
 */
public class FutureResult implements Result {

    private Result result;

    private boolean ready = false;

    public synchronized void setResult(Result result){
        this.result = result;
        this.ready = true;
        this.notifyAll();
    }
    @Override
    public synchronized Object getResultValue() {
        while (!ready){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return this.result.getResultValue();
    }
}
