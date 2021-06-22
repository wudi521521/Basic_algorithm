package com.basic.four;

/**
 * @author Dillon Wu
 * @Description: 数组求和通过递归
 * @date 2021/6/22 15:07
 */
public class Sum {

    public static int sum(int[] arr){
        return sum(arr,0);
    }

    //计算arr[l到n]这个区间内所有数字的和
    private static int sum(int[] arr,int l){
        if (l == arr.length){
            return 0;
        }
        return arr[l]+sum(arr,l+1);
    }

    public static void main(String[] args) {
        int[] nums ={1,2,3,4,5,6,6};
        System.out.println(sum(nums));
    }
}
