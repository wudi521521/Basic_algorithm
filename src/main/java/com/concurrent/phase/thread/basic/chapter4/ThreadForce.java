package com.concurrent.phase.thread.basic.chapter4;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/18 17:43
 */
public class ThreadForce {
    public static void main(String[] args) {
        ThreadService service = new ThreadService();
        long start = System.currentTimeMillis();
        service.execute(()->{
            while (true){

            }
        });
        System.out.println("======main========");
        service.shutDown(10_000);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
