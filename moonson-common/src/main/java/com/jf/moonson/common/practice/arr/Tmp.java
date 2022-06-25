package com.jf.moonson.common.practice.arr;

public class Tmp {

    /**
     * 二分查找
     * <a href="https://leetcode-cn.com/problems/binary-search/">https://leetcode-cn.com/problems/binary-search/</a>
     */
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int middle;
        // 左闭右闭
        while (right >= left) {
            middle = left + ((right - left) >> 1);
            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                return middle;
            }
        }

        return left;
    }


    /**
     * 移除元素
     * <a href="https://leetcode-cn.com/problems/remove-element/">https://leetcode-cn.com/problems/remove-element/</a>
     */
    public static int removeElement(int[] nums, int val) {
        int fast = 0;
        int slow = 0;

        for (; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }

    /**
     * 有序数组的平方根
     * <a href="https://leetcode-cn.com/problems/squares-of-a-sorted-array/">https://leetcode-cn.com/problems/squares-of-a-sorted-array/</a>
     */
    public static int[] sortedSquares(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int[] newArr = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[left] * nums[left] < nums[right] * nums[right]) {
                newArr[i] = nums[right] * nums[right];
                right--;
            } else {
                newArr[i] = nums[left] * nums[left];
                left++;
            }
        }
        return newArr;
    }

    /**
     * 长度最小的子数组
     * <a href="https://leetcode-cn.com/problems/minimum-size-subarray-sum/">https://leetcode-cn.com/problems/minimum-size-subarray-sum/</a>
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0, sum = 0, min = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            sum = sum + nums[right];
            while (sum >= target) {
                min = Math.min((right - left + 1), min);
                sum = sum - nums[left];
                if (left == right) {
                    break;
                }
                left++;
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }

    /**
     * 螺旋矩阵
     * <a href="https://leetcode-cn.com/problems/spiral-matrix-ii/">https://leetcode-cn.com/problems/spiral-matrix-ii/</a>
     */
    public static int[][] generateMatrix(int n) {
        int[][] arr = new int[n][n];
        int index = 1;
        int left = 0;
        int top = 0;
        for (int i = 0; i < n / 2; i++) {
            // 模拟上侧从左到右
            for (int j = left; j < n - i - 1; j++) {
                arr[i][j] = index++;
            }
            // 模拟右侧从上到下
            for (int j = top; j < n - i - 1; j++) {
                arr[j][n - i - 1] = index++;
            }
            // 模拟下侧从右到左
            for (int j = n - 1 - i; j > i; j--) {
                arr[n - i - 1][j] = index++;
            }
            // 模拟左侧从下到上
            for (int j = n - 1 - i; j > top; j--) {
                arr[j][i] = index++;
            }
            left++;
            top++;
        }

        // 最中间的元素
        if (n % 2 == 1) {
            arr[n / 2][n / 2] = index;
        }

        return arr;
    }


    public static void main(String[] args) {

        //        int[] nums = {1, 3, 5, 6};
        //        System.out.println(searchInsert(nums, 4));

        //        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        //        System.out.println(removeElement(nums, 2));
        //        Arrays.stream(nums).asLongStream().forEach(System.out::println);

        //        int[] nums = {-5,-3,-2,-1};
        //        Arrays.stream(sortedSquares(nums)).asLongStream().forEach(System.out::println);

        //
        // int[] nums = {2, 3, 1, 2, 4, 3};
        // System.out.println(minSubArrayLen(7, nums));

        int n = 4;
        int[][] arr = generateMatrix(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }

    }

}
