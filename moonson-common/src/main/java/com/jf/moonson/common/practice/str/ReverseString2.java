package com.jf.moonson.common.practice.str;

/**
 * <a href="https://leetcode.cn/problems/reverse-string-ii/">https://leetcode.cn/problems/reverse-string-ii/</a>
 * 翻转字符串
 */
public class ReverseString2 {

    public static String reverseStr(String s, int k) {
        int left = 0;
        int middle = Math.min(s.length(), k);
        int right = 2 * k - 1;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {

            if (i >= left && i < left + Math.min(k, s.length() - left) / 2) {
                char tmp = chars[i];
                int offset = i - left;
                chars[i] = chars[middle - offset - 1];
                chars[middle - offset - 1] = tmp;
            }
            if (i == right) {
                left = right + 1;
                middle = Math.min(left + k, s.length());
                right = middle + k - 1;
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println(reverseStr(
                "krmyfshbspcgtesxnnljhfursyissjnsocgdhgfxubewllxzqhpasguvlrxtkgatzfybprfmmfithphckksnvjkcvnsqgsgosfxc",
                20));
    }

}
