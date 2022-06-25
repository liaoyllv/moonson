package com.jf.moonson.common.practice.str;

/**
 * https://leetcode.cn/problems/ti-huan-kong-ge-lcof/submissions/
 * 替换空格
 */
public class ReplaceSpace {

    /**
     * 如果想把这道题目做到极致，就不要只用额外的辅助空间了！
     * 首先扩充数组到每个空格替换成"%20"之后的大小。
     * 然后从后向前替换空格，也就是双指针法
     */
    public static String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i) == ' ' ? "%20" : s.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(replaceSpace("we are stupid"));
    }

}
