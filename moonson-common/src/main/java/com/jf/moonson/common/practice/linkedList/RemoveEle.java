package com.jf.moonson.common.practice.linkedList;

/**
 * 删除元素
 * <a href="https://leetcode.cn/problems/remove-linked-list-elements/">https://leetcode.cn/problems/remove-linked-list-elements/</a>
 */
public class RemoveEle {

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


    public static ListNode removeElements(ListNode head, int val) {

        ListNode node = head;
        while (node != null && node.next != null) {
            if (node.next.val == val) {
                node.next = node.next.next;
            }else {
                node = node.next;
            }
        }

        if (head != null && head.val == val) {
            head = head.next;
        }
        return head;

    }

    public static void main(String[] args) {
        ListNode node = new ListNode(7);
        node.next = new ListNode(7);
        node.next.next = new ListNode(7);
        node.next.next.next = new ListNode(7);

        ListNode listNode = removeElements(node, 7);
        System.out.println(listNode.val);

    }

}
