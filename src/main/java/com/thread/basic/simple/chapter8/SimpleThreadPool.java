package com.thread.basic.simple.chapter8;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.springframework.util.CollectionUtils;

import java.security.DigestException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Dillon Wu
 * @Description: 测试线程池
 * @date 2021/8/21 10:19
 */
public class SimpleThreadPool extends Thread {

    private  int size;

    private final int queueSize;

    private final static int DEFAULT_SIZE = 10;

    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;

    private static volatile int seq = 0;

    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    private final static ThreadGroup GROUP = new ThreadGroup("Pool_GROUP");

    //任务队列
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    //线程队列
    private final static List<WorkerTask> THREAD_QUEUE = new ArrayList<>();

    //默认拒绝策略-->抛出异常
    private final DiscardPolicy discardPolicy;

    private volatile boolean destroy = false;

    private int min;

    private int max;

    private int active;

    public final static DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException("Discard this task");
    };

    public SimpleThreadPool() {
        this(DEFAULT_SIZE, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    public SimpleThreadPool(int size, int queueSize, DiscardPolicy discardPolicy) {
        this.size = size;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    /**
     * 初始化线程数
     */
    private void init() {
        System.out.println("========init========");
        for (int i = 0; i < size; i++) {
            createWorkTask();
        }
        this.size = min;
        this.start();
    }

    public void submit(Runnable runnable) {
        if (destroy){
            throw new IllegalArgumentException("The thread pool already destroy and not allow");
        }
        //加锁(因为读写操作同时存在)
        synchronized (TASK_QUEUE) {
            //任务队列中数大于线程数-->执行拒绝策略
            if (TASK_QUEUE.size() > queueSize) {
                discardPolicy.discard();
            }
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    @Override
    public void run() {
        while(destroy){

        }
    }

    private void createWorkTask() {
        WorkerTask task = new WorkerTask(GROUP, THREAD_PREFIX + (seq++));
        task.start();
        //线程队列---添加任务
        THREAD_QUEUE.add(task);
    }

    public void shutDown() throws InterruptedException {
        while (!TASK_QUEUE.isEmpty()) {
            Thread.sleep(50);
        }
        //获取线程的个数
        int initVal = THREAD_QUEUE.size();
        while (initVal > 0) {
            for (WorkerTask task : THREAD_QUEUE) {
                if (task.getTaskState() == TaskState.BLOCKED) {
                    task.interrupt();
                    task.close();
                    initVal--;
                } else {
                    Thread.sleep(10_000);
                }

            }
        }
        this.destroy=true;
        System.out.println("The thread pool disposed");
    }

    public int getSize() {
        return size;
    }

    public int getQueueSize() {
        return queueSize;
    }

    private enum TaskState {
        FREE, RUNNING, BLOCKED, DEAD
    }

    public boolean isDestroy(boolean destroy) {
        return this.destroy = destroy;
    }

    public static class DiscardException extends RuntimeException {
        public DiscardException(String message) {
            super(message);
        }
    }

    /**
     * 拒绝策略
     */
    public interface DiscardPolicy {
        void discard() throws DiscardException;
    }

    /**
     * 工作任务类
     */
    private static class WorkerTask extends Thread {
        //
        private volatile TaskState taskState = TaskState.FREE;

        public WorkerTask(ThreadGroup group, String name) {
            super(group, name);
        }

        public TaskState getTaskState() {
            return this.taskState;
        }

        //关闭线程
        public void close() {
            this.taskState = TaskState.DEAD;
        }

        public void run() {
            OUTER:
            while (this.taskState != TaskState.DEAD) {
                Runnable runnable;
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            //任务状态阻塞
                            taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break OUTER;
                        }
                    }
                    runnable = TASK_QUEUE.removeFirst();
                    if (runnable != null) {
                        //任务运行
                        taskState = TaskState.RUNNING;
                        runnable.run();
                        //任务运行完成
                        taskState = TaskState.FREE;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        SimpleThreadPool threadPool = new SimpleThreadPool(6, 10, DEFAULT_DISCARD_POLICY);
        IntStream.rangeClosed(0, 40)
                .forEach(item -> {
                    threadPool.submit(() -> {
                        System.out.println("The runnable " + item + "be serviced by" + Thread.currentThread() + " start");
                        try {
                            Thread.sleep(1_000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("The runnable " + item + "be serviced by" + Thread.currentThread() + " finished");

                    });
                });
        Thread.sleep(10_000);
        threadPool.shutDown();
        threadPool.submit(()->System.out.println("============="));
    }
}
