package com.jf.moonson.common.practice.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * <p>
 * 「快乐数」定义为：
 * <p>
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 *
 * <a href="https://leetcode.cn/problems/happy-number/">https://leetcode.cn/problems/happy-number/</a>
 */
public class Happy {

    public static boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();

        int tmp;
        int sum = 0;
        while (true) {

            while (true) {
                tmp = n % 10;
                sum += tmp * tmp;
                n = n / 10;
                if (n == 0) {
                    break;
                }
            }
            if (sum == 1) {
                return true;
            } else if (set.contains(sum)) {
                return false;
            } else {
                set.add(sum);
                n = sum;
                sum = 0;
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(isHappy(12));

    }
}
