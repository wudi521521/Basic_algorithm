package com.basic.five;

import java.util.Arrays;

/**
 * @author Dillon Wu
 * @Description: 自低向上的归并排序(自低向上排序)
 * @date 2021/7/1 14:00
 */
public class FloorMergeSort {

    public static <E extends Comparable<E>> void sort(E[] arr) {
        int n = arr.length;
        //遍历合并的区间长度sz+=sz含义sz=sz+sz;
        //sz++含义:sz=sz+1;
        for (int sz=1;sz<n;sz+=sz){
           //遍历合并的区间长度
            //合并[i,i+sz-1]和[i+sz,i+sz+sz-1]
            for (int i=0;i+sz<n;i+=sz+sz){
                //选择最小值，防止索引越界
               merge(arr,i,i+sz-1,Math.min(i+sz+sz-1,n-1));
            }
        }
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        //左边区间
        sort(arr, l, mid);
        //右边区间
        sort(arr, mid + 1, r);
        System.out.println("========================" + "mid" + mid);
        merge(arr, l, mid, r);
    }

    //合并两个有序的区间arr[l,mid]和arr[mid+1,r]
    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r) {
        //前闭后开
        E[] temp = Arrays.copyOfRange(arr, l, r + 1);
        int i = l, j = mid + 1;
        //每轮循环为arr[k]赋值
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = temp[j - l];
                j++;
            } else if (j > r) {
                arr[k] = temp[i - l];
                i++;
            } else if (temp[i - l].compareTo(temp[j - l]) <= 0) {
                arr[k] = temp[i - l];
                i++;
            } else {
                arr[k] = temp[j - l];
                j++;
            }
            //arr[i]和arr[j]

        }
    }

    public static void main(String[] args) {
        //sz++含义:sz=sz+1;
        for (int sz=1;sz<10;sz+=sz){
            System.out.println("************"+sz);
            //遍历合并的区间长度
            //合并[i,i+sz-1]和[i+sz,i+sz+sz-1]
            for (int i=0;i+sz<10;i+=sz+sz){
                //选择最小值，防止索引越界
                System.out.println("============="+i);
            }
        }
    }
}
