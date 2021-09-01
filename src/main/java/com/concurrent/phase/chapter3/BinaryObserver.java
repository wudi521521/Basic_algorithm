package com.concurrent.phase.chapter3;

/**
 * @author Dillon Wu
 * @Description: 具体观察者
 * @date 2021/8/22 20:25
 */
public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject){
        super(subject);
    }
    @Override
    public void update() {
        System.out.println("Binary String"+Integer.toBinaryString(subject.getState()));
    }
}
