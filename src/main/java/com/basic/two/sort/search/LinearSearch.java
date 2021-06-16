package com.basic.two.sort.search;

/**
 * @author Dillon Wu
 * @Description: data数组中找到, target数据在数组对应的哪个索引
 * @date 2021/6/11 14:11
 */
public class LinearSearch {

    public int search(int[] data, int target) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == target) {
                return i;
            }
        }
        //没有数据返回-1
        return -1;
    }

    public static int staticSearch(int[] data, int target) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == target) {
                return i;
            }
        }
        //没有数据返回-1
        return -1;
    }

    /**
     * 数组特性:
     * 一旦初始化长度固定，只能存储一种数据类型,数组中的元素与元素之间的内存地址是连续的
     * 查询快:数组的地址是连续的，通过数组首地址可以找到数组，通过数组索引可以快速查找某一个元素
     * 增删慢:数组长度是固定的，增加/删除一个元素，必须创建一个新数组，把源数组的数据复制过来
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {24, 18, 12, 9, 16, 66, 32, 4};
        LinearSearch ls = new LinearSearch();
        int result = ls.search(data, 16);


    }
}
