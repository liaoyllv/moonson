package com.jf.moonson.common.practice.stack;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyStack {

    Queue<Integer> in;
    Queue<Integer> out;

    void move() {
        while (in.size() > 1) {
            out.add(in.poll());
        }
    }

    public MyStack() {
        in = new LinkedBlockingQueue<>();
        out = new LinkedBlockingQueue<>();
    }

    public void push(int x) {
        if (in.isEmpty()) {
            in.add(x);
        } else {
            while (!in.isEmpty()) {
                out.add(in.poll());
            }
            out.add(x);
        }
    }

    public int pop() {
        if (in.isEmpty()) {
            Queue<Integer> tmp = out;
            out = in;
            in = tmp;
            move();
        }
        return in.poll();

    }

    public int top() {
        if (in.isEmpty()) {
            Queue<Integer> tmp = out;
            out = in;
            in = tmp;
            move();
        }
        return in.peek();

    }

    public boolean empty() {
        return in.isEmpty() && out.isEmpty();

    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack.empty());
    }
}
