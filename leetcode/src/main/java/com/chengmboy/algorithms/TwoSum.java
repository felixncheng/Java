package com.chengmboy.algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * Two Sum
 * 从集合中寻找唯一目标组合
 * @author cheng_mboy
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 13, 7, 15};
        int target = 28;
        int[] ints = twoSum(nums, target);
        System.out.println(ints[0] + " " + ints[1]);
    }

    private static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("no two sum solution ");
    }

}