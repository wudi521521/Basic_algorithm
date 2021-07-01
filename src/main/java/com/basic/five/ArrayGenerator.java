package com.basic.five;

import java.util.Random;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/6/28 19:58
 */
public class ArrayGenerator {

    private ArrayGenerator(){};

    public static Integer[] generateOrderedArray(int n){
        Integer[] arr = new Integer[n];
        for (int i=0;i<n;i++){
            arr[i]=i;
        }
        return arr;
    }

    public static Integer[] generateRandomArray(int n,int bound){
        Integer[] arr = new Integer[n];
        Random rnd = new Random();
        for (int i=0;i<n;i++){
            arr[i]=rnd.nextInt(bound);
        }
        return arr;
    }
}
