package com.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wud
 * @desc 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的 两个 整数。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素
 * @date 2021/9/30 9:16
 * @return 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class SolutionLeetCodeTarget {

    public static void main(String[] args) {
        int[] nums  = new int[]{2,7,11,15};
        int target =9;
        System.out.println(Arrays.toString(solution(nums,target)));
    }

    private static int[] solution(int[] nums,int target){
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            Integer result = target-nums[i];
            System.out.println("result:"+result);
            if (map.containsKey(nums[i])){
                Integer integer = map.get(nums[i]);
                return new int[]{integer,i};
            }else{
                map.put(result,i);
            }
        }
        return new int[]{};
    }
}
