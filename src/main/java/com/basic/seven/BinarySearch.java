package com.basic.seven;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Arrays;

/**
 * @author Dillon Wu
 * @Description: 二分查找法(basic 递归)
 * @date 2021/7/11 17:33
 */
public class BinarySearch {

    private BinarySearch(){}

    /**
     * 递归-二分法
     * @param data
     * @param target
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> int searchR(E[] data,E target){
       return search(data,0,data.length-1,target);
    }

    /**
     *递归二分法
     * @param data 数组
     * @param l 左边索引
     * @param r 右边索引
     * @param target 目标值
     * @param <E>
     * @return
     */
    private static <E extends Comparable<E>> int searchR(E[] data,int l,int r,E target){
        if (l>r) {return -1;}
        int mid =l+(r-l)/2;
        if (data[mid].compareTo(target)==0){
            return mid;
        }

        if (data[mid].compareTo(target)<0){
            return search(data,mid+1,r,target);
        }
        return search(data,l,mid-1,target);
    }

    /**
     * 非递归--二分法
     * @param data
     * @param target
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> int search(E[] data,E target){
        int l=0,r=data.length-1;
        //在data[l,r]的范围中查找target
        while(l<=r){
                 int mid = l+(r-l)/2;
                 if (data[mid].compareTo(target)==0){
                     return mid;
                 }
                 if (data[mid].compareTo(target)<0){
                     l=mid+1;
                 }else {
                     r=mid-1;
                 }
        }
        return -1;
    }

    /**
     *递归二分法
     * @param data 数组
     * @param l 左边索引
     * @param r 右边索引
     * @param target 目标值
     * @param <E>
     * @return
     */
    private static <E extends Comparable<E>> int search(E[] data,int l,int r,E target){
        if (l>r) {return -1;}
        int mid =l+(r-l)/2;
        if (data[mid].compareTo(target)==0){
            return mid;
        }

        if (data[mid].compareTo(target)<0){
            return search(data,mid+1,r,target);
        }
        return search(data,l,mid-1,target);
    }



    public static void main(String[] args) {
        Integer[] arr = {0, 1, 2, 3,5,6,7};

        int search = search(arr, 7);
        System.out.println(search);
    }
}
