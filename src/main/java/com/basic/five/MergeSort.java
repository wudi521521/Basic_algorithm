package com.basic.five;

import java.util.Arrays;

/**
 * @author Dillon Wu
 * @Description:归并算法(自定向下排序)
 * @date 2021/6/28 19:31
 */
public class MergeSort {

    private MergeSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        sort(arr, 0, arr.length - 1);
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

    public static <E extends Comparable<E>> void sort2(E[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void sort2(E[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        //左边区间
        sort(arr, l, mid);
        //右边区间
        sort(arr, mid + 1, r);
        System.out.println("========================" + "mid" + mid);
        //有利于有序数组
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge(arr, l, mid, r);
        }

    }

    public static <E extends Comparable<E>> void sort3(E[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    /**
     * 使用插入排序进行优化
     * @param arr
     * @param l
     * @param r
     * @param <E>
     */
    private static <E extends Comparable<E>> void sort3(E[] arr, int l, int r) {
      /*  if (l >= r) {
            return;
        }*/
        //当数据比较少的时候，使用插入排序法
        if (r - l <= 15) {
            insertSort(arr, l, r);
            return;
        }
        int mid = (l + r) / 2;
        //左边区间
        sort(arr, l, mid);
        //右边区间
        sort(arr, mid + 1, r);
        System.out.println("========================" + "mid" + mid);
        //有利于有序数组
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge(arr, l, mid, r);
        }
    }

    //插入排序
    public static <E extends Comparable<E>> void insertSort(E[] arr, int l, int r) {
        for (int i = l; i <= r; i++) {
            //将arr[i]插入到合适的位置
            E t = arr[i];
            int j;
            for (j = i; j - 1 >= 0 && t.compareTo(arr[j - 1]) < 0; j--) {
                System.out.println("____j____" + j);
                arr[j] = arr[j - 1];
            }
            System.out.println("***j****" + j);
            arr[j] = t;
        }
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E min = arr[i];
        arr[i] = arr[j];
        arr[j] = min;

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

    public static <E extends Comparable<E>> void sort4(E[] arr) {
        E[] temp = Arrays.copyOf(arr,arr.length);
        sort4(arr, 0, arr.length - 1,temp);
    }

    private static <E extends Comparable<E>> void sort4(E[] arr, int l, int r,E[] temp) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        //左边区间
        sort4(arr, l, mid,temp);
        //右边区间
        sort4(arr, mid + 1, r,temp);
        System.out.println("========================" + "mid" + mid);
        merge4(arr, l, mid, r,temp);
    }

    //合并两个有序的区间arr[l,mid]和arr[mid+1,r]
    private static <E extends Comparable<E>> void merge4(E[] arr, int l, int mid, int r,E[] temp) {
        //前闭后开
        //E[] temp = Arrays.copyOfRange(arr, l, r + 1);
        //把数组arr中数据复制一部分到temp数组中
        System.arraycopy(arr,l,temp,l,r-l+1);
        int i = l, j = mid + 1;
        //每轮循环为arr[k]赋值
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = temp[j];
                j++;
            } else if (j > r) {
                arr[k] = temp[i];
                i++;
            } else if (temp[i - l].compareTo(temp[j - l]) <= 0) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            //arr[i]和arr[j]

        }
    }

    public static void main(String[] args) {
        Integer[] arr = {0, 1, 3, 2};

        insertSort(arr, 0, 3);
        Arrays.stream(arr).forEach(item -> {
            System.out.println("=============" + item);
        });


    }
}
