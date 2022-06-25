package com.jf.moonson.common.practice.ds;

public class LinkedListPractice {


    static class Node {

        int data;
        Node next;

        public Node(Node next, int data) {
            this.next = next;
            this.data = data;
        }
    }

    static Node reverse(Node currentNode, Node newHead) {
        if (currentNode == null) {
            return null;
        }

        Node next = currentNode.next;
        if (newHead.next != null) {
            // 如果处理的不是第一个了，则需要处理尾部
            currentNode.next = newHead.next;
            newHead.next = currentNode;
        } else {
            newHead.next = currentNode;
            // 新的尾结点 next 为 null
            newHead.next.next = null;
        }

        if (next != null) {
            return reverse(next, newHead);
        } else {
            // 头结点不需要
            return newHead.next;
        }
    }

    static class PNode {

        int data;
        PNode prev;
        PNode next;

        public PNode(int data, PNode prev, PNode next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    static PNode reversePNode(PNode currentNode, PNode newHead) {
        if (currentNode == null) {
            return null;
        }

        PNode next = currentNode.next;
        if (newHead.next != null) {
            // 如果处理的不是第一个了，则需要处理尾部
            newHead.next.prev = currentNode;
            currentNode.next = newHead.next;
            currentNode.prev = newHead;
            newHead.next = currentNode;
        } else {
            currentNode.prev = newHead;
            newHead.next = currentNode;
            // 新的尾结点 next 为 null
            newHead.next.next = null;
        }

        if (next != null) {
            return reversePNode(next, newHead);
        } else {
            // 头结点不需要
            newHead.next.prev = null;
            return newHead.next;
        }
    }

    static boolean checkCircle(Node currentNode) {
        // 快慢两个引用，如果是环
        Node p1 = currentNode;
        Node p2 = currentNode;
        while (p1.next != null && p1.next.next != null) {
            p1 = p1.next.next;
            p2 = p2.next;
            if (p1 == p2) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        //        Node tail = new Node(null, 1);
        //        Node head = new Node(new Node(tail, 2), 3);
        //
        //        Node newNode = reverse(head, new Node(null, 0));
        //        while (newNode != null) {
        //            System.out.println(newNode.data);
        //            newNode = newNode.next;
        //        }

        //        PNode tail = new PNode(1, null,null);
        //        PNode middle = new PNode(2, null,tail);
        //        tail.prev = middle;
        //        PNode head = new PNode(3, null,middle);
        //        middle.prev = head;
        //
        //        PNode newNode = reversePNode(head, new PNode(0, null,null));
        //        while (newNode != null) {
        //            System.out.println(newNode.data);
        //            newNode = newNode.next;
        //        }


        Node tail = new Node(null, 1);
        Node head = new Node(new Node(tail, 2), 3);
        tail.next = head;
        System.out.println(checkCircle(head));;

    }


}
