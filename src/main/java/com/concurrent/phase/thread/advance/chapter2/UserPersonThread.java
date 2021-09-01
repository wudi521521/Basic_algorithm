package com.concurrent.phase.thread.advance.chapter2;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/23 13:14
 */
public class UserPersonThread extends Thread{

    private  Person person;

    public UserPersonThread(Person person){
        this.person = person;
    }

    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName()+" print "+person.getAddress());
        }
    }
}
