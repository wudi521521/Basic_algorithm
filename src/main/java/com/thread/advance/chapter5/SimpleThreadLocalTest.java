package com.thread.advance.chapter5;

import scala.Int;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/23 19:48
 */
public class SimpleThreadLocalTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "Aluex";
        }
    };

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(1_000);
        String value = threadLocal.get();
        System.out.println(value);
    }
}
