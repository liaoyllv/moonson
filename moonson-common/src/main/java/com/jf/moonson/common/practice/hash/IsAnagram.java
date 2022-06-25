package com.jf.moonson.common.practice.hash;

/**
 * 有效的字母异位词
 * 给定两个字符串 s 和 t，若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 * <a href="https://leetcode.cn/problems/valid-anagram/">https://leetcode.cn/problems/valid-anagram/</a>
 */
public class IsAnagram {

    public static boolean isAnagram(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }

        byte[] arr = new byte[26];


        for (int i = 0; i < s.length(); i++) {
            arr[(byte) s.charAt(i) - 97]++;
            arr[(byte) t.charAt(i) - 97]--;
        }

        for (int i = 0; i < 26; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        // System.out.println((byte) 'a');

        System.out.println(isAnagram("aaba", "bbaa"));
    }


}
