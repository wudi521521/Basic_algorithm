package com.concurrent.phase.thread.advance.chapter3;

/**
 * @author Dillon Wu
 * @Description: future design pattern
 * @date 2021/8/23 14:24
 *
 * Future --> 代表的是未来的一个凭据
 * FutureTask--->将你的调用逻辑进行了隔离
 * FutureService-->桥接 Future 和 FutureTask
 */
public class SynInvoker {
    public static void main(String[] args) throws InterruptedException{
        //1:执行任务
        FutureService futureService = new FutureService();
        //韩式式编程
        Future<String> submit = futureService.submit(() -> {
            try {
                System.out.println("main submit");
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISH";
        }, (x)->{
            System.out.println(x+" 你好函数式接口");
        });
        System.out.println("--------------------");
        System.out.println("----------------------------");
        //2:执行调用(阻塞)
        System.out.println(submit.get());
    }

    private static String get() throws InterruptedException{
        Thread.sleep(10_000);
        return "Finish";
    }
}
