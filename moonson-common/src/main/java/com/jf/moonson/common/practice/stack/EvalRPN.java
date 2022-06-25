package com.jf.moonson.common.practice.stack;

import java.util.Stack;

/**
 * <a href="https://leetcode.cn/problems/evaluate-reverse-polish-notation/">https://leetcode.cn/problems/evaluate-reverse-polish-notation/</a>
 * 逆波兰表达式求值
 */
public class EvalRPN {

    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int y = stack.pop();
                int x = stack.pop();
                int z;
                switch (token) {
                    case "+":
                        z = x + y;
                        break;
                    case "-":
                        z = x - y;
                        break;
                    case "*":
                        z = x * y;
                        break;
                    default:
                        z = x / y;
                        break;
                }
                stack.push(z);
            } else {
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        args = new String[]{"2", "1", "+", "3", "*"};
        System.out.println(evalRPN(args));
    }

}
