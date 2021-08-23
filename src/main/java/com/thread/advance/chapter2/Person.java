package com.thread.advance.chapter2;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/23 13:09
 */
final public class Person {

    private final String name;

    private final String address;

    public Person(final String name,final String address){
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
