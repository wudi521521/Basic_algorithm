package com.concurrent.phase.thread.basic.chapter2;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/17 19:54
 */
public class DemoConcurrent extends Thread {

    private static final int Max = 50;
    private static int index=1;

    private String name;

    public DemoConcurrent(String name){
        this.name=name;
    }

    @Override
    public void run() {
        while(index <=Max){
            System.out.println(name+"当前的号码:"+index++);
        }
    }

    public static void main(String[] args) {
        DemoConcurrent concurrent = new DemoConcurrent("The Thread of NO 1");
        concurrent.start();
        DemoConcurrent concurrent2 = new DemoConcurrent("The Thread of NO 2");
        concurrent2.start();
        DemoConcurrent concurrent3 = new DemoConcurrent("The Thread of NO 3");
        concurrent3.start();
    }
}
