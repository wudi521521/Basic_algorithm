package com.second.phase.chapter3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/22 20:18
 */
public class Subject {

    private List<Observer> observers = new ArrayList<>();

    private int state;

    public int getState(){
        return this.state;
    }

    public void setState(int state){
        if (state ==this.state){
            return;
        }
        this.state=state;
        //发送信息到观察者
        notifyAllowObserver();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    private void notifyAllowObserver(){
        observers.stream().forEach(Observer::update);
    }
}
