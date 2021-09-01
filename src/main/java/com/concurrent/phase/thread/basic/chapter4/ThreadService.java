package com.concurrent.phase.thread.basic.chapter4;

/**
 * @author Dillon Wu
 * @Description:强制关闭线程
 * @date 2021/8/18 17:30
 */
public class ThreadService {
    //执行线程
    private Thread executeThread;

    private boolean finished = false;

    public void execute(Runnable task) {
        //线程
        executeThread = new Thread() {
            @Override
            public void run() {
                //定义执行线程的守护线程 作用:执行线程完成,守护线程需要自动销毁
                Thread runner = new Thread(task);
                runner.setDaemon(true);
                runner.start();
                try {
                    System.out.println("守护线程");
                    //守护线程需要执行完成,主线程才可以唤醒
                    runner.join();
                    finished = true;
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
                System.out.println("执行线程");

            }
        };
        executeThread.start();

    }

    public void shutDown(long millis) {
        long currentTime = System.currentTimeMillis();
        while (!finished) {
            if ((System.currentTimeMillis() - currentTime >= millis)) {
                System.out.println("任务超时，需要结束他");
                //线程中断
                executeThread.interrupt();
                break;
            }
            try {
                executeThread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断");
                break;
            }
        }
        finished = false;
    }

    public static void main(String[] args) {

    }
}
