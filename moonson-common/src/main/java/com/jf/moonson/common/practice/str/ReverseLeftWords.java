package com.jf.moonson.common.practice.str;

/**
 * https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/
 * 左旋
 */
public class ReverseLeftWords {

    public String reverseLeftWords(String s, int n) {
        String s1 = s.substring(0, n);
        String s2 = s.substring(n);
        return s1 + s2;
    }

    public static void main(String[] args) {

    }

}
