package com.concurrent.phase.thread.completion;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/2 10:37
 */
public class ScheduleExecutorExample2 {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(2);
        //固定时间的延迟
        executorService.scheduleWithFixedDelay(() -> System.out.println("++++++"), 1, 2, TimeUnit.SECONDS);
    }

    private static void testScheduleWithFixedDelay(){}
}
