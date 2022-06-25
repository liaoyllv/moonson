package com.jf.moonson.common.practice.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
 *
 * <a href="https://leetcode.cn/problems/two-sum/">https://leetcode.cn/problems/two-sum/</a>
 */
public class TowSum {

    public static int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>(nums.length * 2);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }


        return new int[]{};
    }

    public static void main(String[] args) {
        int[] ints = twoSum(new int[]{3, 3}, 6);
        System.out.println(ints[0] + ", " + ints[1]);

    }

}
