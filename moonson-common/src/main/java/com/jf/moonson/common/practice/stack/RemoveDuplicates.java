package com.jf.moonson.common.practice.stack;

import java.util.Stack;

/**
 * <a href="https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string/">https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string/</a>
 * 删除字符串中的所有相邻重复项
 */
public class RemoveDuplicates {

    public static String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!stack.isEmpty() && stack.peek().equals(c)) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        char[] arr = new char[stack.size()];
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = stack.pop();
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates("abbaca"));

    }
}
