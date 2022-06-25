package com.jf.moonson.common.practice.str;


/**
 * https://leetcode.cn/problems/implement-strstr/
 * IndexOf()
 */
public class IndexOf {

    /**
     * 初始化模式串next数组，最长相等前后缀表
     */
    public static int[] initiate(String ps) {
        char[] p = ps.toCharArray();
        int[] next = new int[ps.length()];
        next[0] = 0;

        // j 为前缀末尾，i 为后缀末尾
        // 初始位置为 0
        int j = 0;
        for (int i = 1; i < ps.length() - 1; i++) {

            // 遇见冲突则回退
            while (j > 0 && p[i] != p[j]) {
                // 回退到上一个
                j = next[j - 1];
            }

            if (p[i] == p[j]) {
                // 相等则后移
                j++;
                // 更新 next 数组值
                next[i] = j;
            }
        }
        return next;
    }

    private static int strStr(String a, String b) {
        if (a.length() < b.length()) {
            return -1;
        }
        int[] next = initiate(b);

        char[] arrayA = a.toCharArray();
        char[] arrayB = b.toCharArray();

        int j = 0;
        for (int i = 0; i < a.length(); i++) {
            while (arrayA[i] != arrayB[j] && j > 0) {
                j = next[j - 1];
            }

            if (arrayA[i] == arrayB[j]) {
                // 是否为最后一个字符则成功匹配
                if (j == b.length() - 1) {
                    return i - j;
                } else {
                    j++;
                }
            }
        }

        return -1;
    }


    public static void main(String[] args) {
        //        Arrays.stream(initiate("aabaaf")).asLongStream().forEach(System.out::println);

        System.out.println(strStr("aabaabaafa", "aabaaf"));
        System.out.println(strStr("aafaab", "aab"));
    }


}
