package com.second.phase.chapter3;

/**
 * @author Dillon Wu
 * @Description:抽象观察者
 * @date 2021/8/22 20:22
 */
public abstract class Observer {


    protected Subject subject;
    public Observer(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    public abstract void update();
}
