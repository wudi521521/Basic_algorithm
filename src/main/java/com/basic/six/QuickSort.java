package com.basic.six;

import java.util.Arrays;

/**
 * @author Dillon Wu
 * @Description: 快速排序（basic）
 * @date 2021/7/2 11:05
 */
public class QuickSort {

    private QuickSort() {
    }

    /**
     * 公共接口
     *
     * @param arr
     * @param <E>
     */
    public static <E extends Comparable<E>> void sort(E[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        //对标点位置
        int p = partition(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);

    }

    /**
     * 返回的是j是标记点
     *
     * @param arr
     * @param l
     * @param r
     * @param <E>
     * @return
     */
    private static <E extends Comparable<E>> int partition(E[] arr, int l, int r) {
        System.out.println("****************"+l);
        //arr[l+1...j]<v;arr[j+1...i)>=V
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            //右边大于标记数据
            if (arr[i].compareTo(arr[l]) < 0) {
                j++;
                swap(arr, i, j,"A");
            }
        }
        //
        swap(arr, l, j,"B");
        return j;
    }

    private static <E> void swap(E[] arr, int i, int j,String sign) {
        E t = arr[i];
        E jt=arr[j];
        arr[i] = arr[j];
        arr[j] = t;
        System.out.println("tttttt{}"+sign+"====t:"+t+"===jt="+jt+"===j==="+j+"====i======"+i);
    }

    public static void main(String[] args) {
        Integer[] arr = {0, 1, 7, 2,6,5,4};

        sort(arr);
        Arrays.stream(arr).forEach(item -> {
            System.out.println("=============" + item);
        });

    }
}
