package com.jf.moonson.common.practice.str;

/**
 * https://leetcode.cn/problems/reverse-words-in-a-string/
 * 翻转单词
 */
public class ReverseWords {

    public static String reverseWords(String s) {

        int newIndex = s.length() - 1, oldIndex = 0;
        char[] chars = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                continue;
            }
            if (i == 0 || s.charAt(i - 1) == ' ') {
                oldIndex = i;
            }
            if (i == s.length() - 1 || s.charAt(i + 1) == ' ') {
                // 到达末尾或者单词结尾
                for (int j = i; j >= oldIndex; j--) {
                    chars[newIndex--] = s.charAt(j);
                }
                if (newIndex > 0) {
                    chars[newIndex--] = ' ';
                }
            }
        }
        if (newIndex > -1) {
            // 存在多余空格
            // 找到开始的位置
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] != '\u0000' && chars[i] != ' ') {
                    index = i;
                    break;
                }
            }
            char[] newChars = new char[s.length() - index];
            for (int i = index; i < s.length(); i++) {
                newChars[i - index] = chars[i];
            }
            return new String(newChars);
        }
        return new String(chars);
    }

    public static String reverseWords2(String s) {
        int left = 0, right = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (i == s.length() - 1 || s.charAt(i + 1) == ' ') {
                right = i;
                // 到达末尾或者单词结尾

                for (int j = left; j <= left + (right - left) / 2; j++) {
                    char tmp = s.charAt(j);
                    chars[j] = s.charAt(right - (j - left));
                    chars[right - (j - left)] = tmp;
                }

                if (i < s.length() - 2) {
                    left = right + 2;
                }
            }
        }
        return new String(chars);
    }


    public static void main(String[] args) {
        System.out.println(reverseWords("   the   sky   is  blue "));
    }
}
