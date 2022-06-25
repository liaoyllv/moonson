package com.jf.moonson.common.practice.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/4sum/">https://leetcode.cn/problems/4sum/</a>
 */
public class FourSum {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<>();

        if (nums.length < 4) {
            return lists;
        }
        Arrays.sort(nums);
        for (int i1 = 0; i1 < nums.length - 3; i1++) {
            if (i1 > 0 && nums[i1] == nums[i1 - 1]) {
                continue;
            }

            for (int i2 = i1 + 1; i2 < nums.length - 2; i2++) {
                if (i2 > i1 + 1 && nums[i2] == nums[i2 - 1]) {
                    continue;
                }

                int i3 = i2 + 1;
                int i4 = nums.length - 1;
                while (i3 < i4) {
                    if (i3 > i2 + 1 && nums[i3] == nums[i3 - 1]) {
                        i3++;
                        continue;
                    }

                    int sum = nums[i1] + nums[i2] + nums[i3] + nums[i4];
                    if (sum < target) {
                        i3++;
                    } else if (sum > target) {
                        i4--;
                    } else {
                        lists.add(Arrays.asList(nums[i1], nums[i2], nums[i3], nums[i4]));
                        while (i4 > i3 && nums[i4] == nums[i4 - 1]) i4--;
                        while (i4 > i3 && nums[i3] == nums[i3 + 1]) i3++;
                        i3++;
                        i4--;
                    }
                }

            }
        }
        return lists;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = fourSum(new int[]{1,-2,-5,-4,-3,3,3,5}, -11);
        System.out.println(lists.size());
    }
}
