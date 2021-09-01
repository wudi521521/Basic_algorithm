package com.concurrent.phase.chapter3;

import java.util.Arrays;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/22 21:02
 */
public class ThreadLifeCycleClient {
    public static void main(String[] args) {
         new ThreadLifeCycleListener().concurrentQuery(Arrays.asList("1","2","3"));

    }
}
