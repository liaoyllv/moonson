package com.jf.moonson.common.practice.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/ransom-note/">https://leetcode.cn/problems/ransom-note/</a>
 */
public class CanConstruct {


    /**
     * 都是字母可以优化为数组
     */
    public static boolean canConstruct(String ransomNote, String magazine) {

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < magazine.length(); i++) {
            char key = magazine.charAt(i);
            Integer val = map.getOrDefault(key, 0) + 1;
            map.put(key, val);
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            char key = ransomNote.charAt(i);
            int val = map.getOrDefault(key, 0) - 1;
            if (val < 0) {
                return false;
            }
            map.put(key, val);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(canConstruct("abb", "ab"));

    }
}
