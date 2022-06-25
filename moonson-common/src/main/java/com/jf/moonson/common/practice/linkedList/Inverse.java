package com.jf.moonson.common.practice.linkedList;

/**
 * 翻转链表
 * <a href="https://leetcode.cn/problems/reverse-linked-list/">https://leetcode.cn/problems/reverse-linked-list/</a>
 */
public class Inverse {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode current = head;
        ListNode next = current.next;
        ListNode next2;

        while (true) {
            if (next == null) {
                break;
            }

            next2 = next.next;
            next.next = current;
            current = next;
            next = next2;
        }

        head.next = null;
        return current;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);

        reverseList(node);
    }


}
