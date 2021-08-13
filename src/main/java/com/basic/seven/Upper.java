package com.basic.seven;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/7/12 13:57
 */
public class Upper {

    private Upper() {
    }

    /**
     * 递归-二分法
     *
     * @param data
     * @param target
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> int search(E[] data, E target) {
        return search(data, 0, data.length - 1, target);
    }

    /**
     * 递归二分法
     *
     * @param data   数组
     * @param l      左边索引
     * @param r      右边索引
     * @param target 目标值
     * @param <E>
     * @return
     */
    private static <E extends Comparable<E>> int search(E[] data, int l, int r, E target) {
        if (l > r) {
            return -1;
        }
        int mid = l + (r - l) / 2;
        if (data[mid].compareTo(target) == 0) {
            return mid;
        }

        if (data[mid].compareTo(target) < 0) {
            return search(data, mid + 1, r, target);
        }
        return search(data, l, mid - 1, target);
    }

    //>target 最小值的索引值
    public static <E extends Comparable<E>> int upper(E[] data, E target) {
        int l = 0, r = data.length;
        //在data[l,r]中寻找解
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (data[mid].compareTo(target) <= 0) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    /**
     *
     *
     * @param data
     * @param target
     * @param <E>
     * @return
     */
    //> target,返回最小值索引
    //== target，返回最大索引值
    public static <E extends Comparable<E>> int ceil(E[] data, E target){
        int u = upper(data,target);
        if (u-1>=0 && data[u-1].compareTo(target)==0){
            return u-1;
        }
        return u;
    }
    
}
