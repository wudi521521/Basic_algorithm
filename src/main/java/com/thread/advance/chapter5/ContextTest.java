package com.thread.advance.chapter5;

import java.util.stream.IntStream;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/23 20:26
 */
public class ContextTest {
    public static void main(String[] args) {
        IntStream.range(1,5).forEach(i->{
            new Thread(new ExecutionTask()).start();
        });
    }
}
