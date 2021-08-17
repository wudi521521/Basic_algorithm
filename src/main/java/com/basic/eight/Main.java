package com.basic.eight;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/13 15:01
 */
public class Main {

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {5,3,6,8,4,2};
        for(int num:nums){
            bst.add(num);
        }
        bst.preOrder();
    }
}
