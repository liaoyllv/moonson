package com.jf.moonson.common.practice.str;

/**
 * https://programmercarl.com/0028.%E5%AE%9E%E7%8E%B0strStr.html
 */
public class KMP {

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

    private static boolean kmp(String a, String b) {
        if (a.length() < b.length()) {
            return false;
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
                    return true;
                }else {
                    j++;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
//        Arrays.stream(initiate("aabaaf")).asLongStream().forEach(System.out::println);

        System.out.println(kmp("aabaabaafa", "abaaba"));
        System.out.println(kmp("aafaab", "aab"));
    }


}
