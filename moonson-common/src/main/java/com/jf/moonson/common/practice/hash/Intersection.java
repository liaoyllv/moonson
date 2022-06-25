package com.jf.moonson.common.practice.hash;

/**
 * 两个数组的交集
 * <a href="https://leetcode.cn/problems/intersection-of-two-arrays/">https://leetcode.cn/problems/intersection-of-two-arrays/</a>
 */
public class Intersection {

    public static int[] intersection(int[] nums1, int[] nums2) {

        int[] arr = new int[1000];
        for (int i = 0; i < nums1.length; i++) {
            if (arr[nums1[i]] == 0) {
                arr[nums1[i]]++;
            }
        }

        int size = 0;
        for (int i = 0; i < nums2.length; i++) {
            if (arr[nums2[i]] == 1) {
                arr[nums2[i]]++;
                size++;
            }
        }

        int[] res = new int[size];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 2) {
                res[index++] = i;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] intersection = intersection(new int[]{1, 2, 3, 4, 1}, new int[]{2, 5, 3});
        System.out.println(intersection);

    }

}
