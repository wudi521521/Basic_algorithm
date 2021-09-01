package com.concurrent.phase.thread.advance.chapter10;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/24 13:47
 */
public class CounterTest {
    public static void main(String[] args) throws InterruptedException{
        CounterIncrement counterIncrement = new CounterIncrement();
        counterIncrement.start();
        Thread.sleep(10_000);
        counterIncrement.close();
    }
}
