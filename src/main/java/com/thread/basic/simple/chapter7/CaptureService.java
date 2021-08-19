package com.thread.basic.simple.chapter7;

import javax.naming.ldap.Control;
import java.util.*;

/**
 * @author Dillon Wu
 * @Description: 自定义线程池
 * @date 2021/8/19 15:54
 */
public class CaptureService {

    final static private LinkedList<Control> CONTROLS = new LinkedList();

    private final  static int MAX=5;

    public static void main(String[] args) {
        List<Thread> worker = new ArrayList<>();
        Arrays.asList("M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10").stream()
                .map(CaptureService::createCaptureThread)
                .forEach(item -> {
                    item.start();
                    worker.add(item);
                });
        worker.stream().forEach(item -> {
            try {
                item.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Optional.of("All of capture work finished").ifPresent(System.out::println);
    }

    private static Thread createCaptureThread(String name) {
        return new Thread(() -> {
            Optional.of("The worker [" + Thread.currentThread().getName() + "]").ifPresent(System.out::println);
            synchronized (CONTROLS) {
                while (CONTROLS.size() > MAX) {
                    try {
                        CONTROLS.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //添加数据含义：消耗的线程数
                CONTROLS.addLast(new Control());
            }

            Optional.of("The worker [" + Thread.currentThread().getName() + "] is working").ifPresent(System.out::println);
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (CONTROLS){
                Optional.of("The worker [" + Thread.currentThread().getName() + "] END capture data").ifPresent(System.out::println);
                CONTROLS.removeFirst();
                CONTROLS.notifyAll();
            }
        }, name);
    }

    private static class Control {
    }
}
