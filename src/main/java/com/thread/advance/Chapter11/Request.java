package com.thread.advance.Chapter11;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/25 9:42
 */
public class Request {
    private final String name;

    private final int number;

    public Request(String name,int number){
        this.name =name;
        this.number = number;
    }

    public void executor(){
        System.out.println(Thread.currentThread().getName()+" executed"+this);
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
