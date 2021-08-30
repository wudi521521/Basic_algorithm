package com.thread.three.automic.exchanger;

import javafx.beans.binding.ObjectExpression;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/30 11:47
 */
public class ExchangeExample2 {
    /**
     * Actor
     *
     * @param args
     */
    public static void main(String[] args) {
        final Exchanger<Object> exchanger = new Exchanger<>();
        new Thread(new Runnable() {
            @Override
            public void run() {

                Object exchange = null;
                Object object = new Object();
                System.out.println("A will send the Object"+object);
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                    exchange = exchanger.exchange(object);
                    System.out.println("A received the object"+Thread.currentThread().getName()+" Get Value "+exchange);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"===A===").start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                Object exchange = null;
                Object object = new Object();
                System.out.println("B will send the Object"+object);
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                    exchange = exchanger.exchange(object);
                    System.out.println("B received the object"+Thread.currentThread().getName()+" Get Value "+exchange);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"===B===").start();

    }
}
