package com.thread.advance.chapter8;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/24 11:14
 */
public class CountDown {

    private final int total;

    private int count;

    public CountDown(int total){
        this.total = total;
    }

    /**
     * 减少
     */
    public void down(){
        synchronized (this){
            this.count++;
            this.notifyAll();
        }
    }

    /**
     * 即将发生
     * @throws InterruptedException
     */
    public void await() throws InterruptedException{
        System.out.println("CountDown await");
        synchronized (this){
            //计数器不相等进入
            while(count !=total){
                this.wait();
                System.out.println("========CountDown await============"+Thread.currentThread().getName());
            }
            System.out.println("========CountDown await  end============");

        }
    }
}
