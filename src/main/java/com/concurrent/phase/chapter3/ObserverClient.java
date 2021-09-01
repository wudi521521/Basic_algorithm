package com.concurrent.phase.chapter3;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/22 20:31
 */
public class ObserverClient {
    public static void main(String[] args) {
        final Subject subject = new Subject();
        new BinaryObserver(subject);
        new OctalObserver(subject);
        System.out.println("===============");
        subject.setState(10);
        System.out.println("------------------");
        subject.setState(10);
        System.out.println("*************");
        subject.setState(15);
    }
}
