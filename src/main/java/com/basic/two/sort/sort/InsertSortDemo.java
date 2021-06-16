package com.basic.two.sort.sort;

import java.util.Arrays;

/**
 * @author Dillon Wu
 * @Description: 插入排序法
 * @date 2021/6/15 19:02
 */
public class InsertSortDemo {

    private InsertSortDemo() {
    }

    //[0,i)进行排序，[i,n)不进行处理 插入排序1
    public static <E extends Comparable<E>> void sort(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //将arr[i]插入到合适的位置
            for (int j = i; j - 1 >= 0; j--) {
                //当前索引对应的值比前一个值小，插入到前面那个数据
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    swap(arr, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }
    //[0,i)进行排序，[i,n)不进行处理 插入排序2
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //将arr[i]插入到合适的位置
            E t =arr[i];
            int j;
            for (j=i;j>=0 && t.compareTo(arr[j])==-1;j--){
                 arr[j]=arr[j-1];
            }
            arr[j]=t;
        }
    }

    //[0,i)进行排序，[i,n)不进行处理 插入排序3
    public static <E extends Comparable<E>> void sort3(E[] arr) {

            //将arr[i]插入到合适的位置
            for (int i=arr.length-1;i>=0 ;i--){
                E t = arr[i];
                int j;
                for(j = i; j + 1 < arr.length && t.compareTo(arr[j + 1]) > 0; j ++){
                    arr[j] = arr[j + 1];
                }
                arr[j]=t;
            }


    }



    private static <E> void swap(E[] arr, int i, int j) {
        E min = arr[i];
        arr[i] = arr[j];
        arr[j] = min;

    }

    public static void main(String[] args) {
        Integer[] arr = {1, 3, 2, 19, 3};
        InsertSortDemo.sort3(arr);
        Arrays.stream(arr).forEach(integer -> {
            System.out.println(integer);
        });
    }
}
