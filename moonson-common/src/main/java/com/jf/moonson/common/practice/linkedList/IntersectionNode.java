package com.jf.moonson.common.practice.linkedList;

/**
 * 链表相交
 * <a href="https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/">https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/</a>
 */
public class IntersectionNode {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        int sizeA = 0;
        int sizeB = 0;
        ListNode fastA = headA;
        ListNode fastB = headB;
        while (true) {
            if (fastA != null) {
                fastA = fastA.next;
                sizeA++;
            }
            if (fastB != null) {
                fastB = fastB.next;
                sizeB++;
            }
            if (fastA == null && fastB == null) {
                break;
            }
        }

        ListNode largerNode = sizeA > sizeB ? headA : headB;
        ListNode smallerNode = sizeA > sizeB ? headB : headA;
        for (int i = 0; i < Math.abs(sizeA - sizeB); i++) {
            largerNode = largerNode.next;
        }

        for (int i = 0; i < Math.min(sizeA, sizeB); i++) {
            if (largerNode == smallerNode) {
                return largerNode;
            }
            largerNode = largerNode.next;
            smallerNode = smallerNode.next;
        }
        return null;
    }

    public static void main(String[] args) {

        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        a1.next = a2;
        a2.next = a3;

        ListNode b1 = new ListNode(10);
        ListNode b2 = new ListNode(11);
        b1.next = b2;
        // b2.next = a1;

        ListNode node = getIntersectionNode(a1, b1);
        if (node != null) {
            System.out.println(node.val);
        }
    }
}
