package com.thread.basic.simple.chapter6;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/19 11:53
 */
public class Test {
    private static  Integer index=1;
    public static void main(String[] args) {
       List<Integer> list = new ArrayList<>();
        IntStream.range(1,100).forEach(item->{
            list.add(item);

        });

        list.stream().forEach(item->{
            index=item+index;
        });
        System.out.println(index);
    }
}
