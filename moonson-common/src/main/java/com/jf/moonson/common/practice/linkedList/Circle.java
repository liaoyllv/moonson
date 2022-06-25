package com.jf.moonson.common.practice.linkedList;

/**
 * 环形链表 II
 * <a href="https://leetcode.cn/problems/linked-list-cycle-ii/">https://leetcode.cn/problems/linked-list-cycle-ii/</a>
 */
public class Circle {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        int circleSize = 0;
        ListNode fast = head;
        ListNode slow = head;
        while (true) {
            if (fast.next == null || fast.next.next == null) {
                // 没有环
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;

            // 首次发现环
            if (fast == slow) {
                // 计算环的size
                ListNode slowCopy = slow;
                while (true) {
                    slow = slow.next;
                    circleSize++;
                    if (slow == slowCopy) {
                        break;
                    }
                }
                break;
            }
        }
        // fast 先走 circleSize
        fast = head;
        slow = head;
        for (int i = 0; i < circleSize - 1; i++) {
            fast = fast.next;
        }
        while (true) {
            if (fast.next == slow) {
                return slow;
            }
            fast = fast.next;
            slow = slow.next;
        }
        
    }

    public static void main(String[] args) {

        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);

        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        // a4.next = a1;

        ListNode listNode = detectCycle(a1);
        System.out.println(listNode.val);
    }

}
