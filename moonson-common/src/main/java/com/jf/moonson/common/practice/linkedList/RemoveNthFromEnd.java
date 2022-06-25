package com.jf.moonson.common.practice.linkedList;

/**
 * 删除链表的倒数第 N 个结点
 * <a href="https://leetcode.cn/problems/remove-nth-node-from-end-of-list/">https://leetcode.cn/problems/remove-nth-node-from-end-of-list/</a>
 */
public class RemoveNthFromEnd {

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

    /**
     * 正确思路如下：双指针的经典应用，如果要删除倒数第n个节点，让fast移动n步，
     * 然后让fast和slow同时移动，直到fast指向链表末尾。删掉slow所指向的节点就可以了。
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {

        if (head == null || n < 1) {
            return head;
        }

        ListNode current = head;
        int size = 1;
        while (true) {
            if (current.next == null) {
                break;
            }
            current = current.next;
            size++;
        }

        if (size - n < 0) {
            return head;
        } else if (size == n) {
            // 删除第一个
            head = head.next;
        }else {
            ListNode prev = head;
            for (int i = 0; i < size ; i++) {
                if (i == (size - n - 1)) {
                    prev.next = prev.next.next;
                    break;
                }
                prev = prev.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        removeNthFromEnd(node, 2);
    }

}
