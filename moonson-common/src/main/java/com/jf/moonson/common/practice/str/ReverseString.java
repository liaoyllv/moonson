package com.jf.moonson.common.practice.str;

/**
 * <a href="https://leetcode.cn/problems/reverse-string/submissions/">https://leetcode.cn/problems/reverse-string/submissions/</a>
 * 翻转字符串
 */
public class ReverseString {
    public static void reverseString(char[] s) {
        int middle = s.length / 2;
        for (int i = 0; i < middle; i++) {
            char tmp = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - i - 1] = tmp;
        }

    }

    public static void main(String[] args) {
        reverseString(new char[]{'h', 'e', 'l', 'l', 'o'});
    }
}
