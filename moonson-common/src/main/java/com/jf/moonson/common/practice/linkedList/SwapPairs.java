package com.jf.moonson.common.practice.linkedList;

/**
 * 两两交换链表中的节点
 * <a href="https://leetcode.cn/problems/swap-nodes-in-pairs/">https://leetcode.cn/problems/swap-nodes-in-pairs/</a>
 */
public class SwapPairs {

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

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode realHead = head.next;
        ListNode prev = null;

        ListNode current = head;
        while (true) {
            if (current == null || current.next == null) {
                break;
            }
            ListNode next = current.next;

            if (prev != null) {
                prev.next = next;
            }
            // 为下一次处理保存 prev
            prev = current;

            current.next = next.next;
            next.next = current;
            current = current.next;
        }
        return realHead;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        // node.next.next.next = new ListNode(4);

        swapPairs(node);
    }


}
