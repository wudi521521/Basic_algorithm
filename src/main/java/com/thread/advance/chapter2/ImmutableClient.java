package com.thread.advance.chapter2;

import java.util.stream.IntStream;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/23 13:16
 */
public class ImmutableClient {
    public static void main(String[] args) {
        //Share Data
        Person person = new Person("Alx", "beijing");

        IntStream.range(0,5).forEach(item->{
            new UserPersonThread(person).start();
        });

    }
}
