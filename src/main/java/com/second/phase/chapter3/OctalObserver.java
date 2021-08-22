package com.second.phase.chapter3;

/**
 * @author Dillon Wu
 * @Description: 具体观察者
 * @date 2021/8/22 20:27
 */
public class OctalObserver extends Observer {

    public OctalObserver(Subject subject){
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Octal String"+Integer.toOctalString(subject.getState()));

    }
}
