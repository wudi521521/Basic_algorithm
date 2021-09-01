package com.concurrent.phase.thread.forjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/31 11:21
 */
public class ForkJoinRecursiveAction {

    private final static int MAX_THRESHOLD=3;

    public static void main(String[] args) {

    }

    private static class CalculationRecursiveAction extends RecursiveTask{
        //上标
        private final int start;

        //下标
        private final int end;

        private CalculationRecursiveAction(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Object compute() {
            if ((end-start)<=MAX_THRESHOLD){
            }
            return null;
        }
    }
}
