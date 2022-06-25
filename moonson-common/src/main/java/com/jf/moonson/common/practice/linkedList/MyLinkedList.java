package com.jf.moonson.common.practice.linkedList;

/**
 * 设计链表
 * <a href="https://leetcode.cn/problems/design-linked-list/">https://leetcode.cn/problems/design-linked-list/</a>
 */
public class MyLinkedList {

    private Node head;
    private Node tail;

    static class Node {

        private int val;
        private Node next;
        private Node prev;

    }


    public MyLinkedList() {

    }

    public int get(int index) {
        if (head == null) {
            return -1;
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
            if (current == null) {
                return -1;
            }
        }
        return current.val;
    }

    public void addAtHead(int val) {
        Node node = new Node();
        node.val = val;
        if (head == null) {
            head = node;
            tail = node;
        } else {
            head.prev = node;
            node.next = head;
            head = node;
        }
    }

    public void addAtTail(int val) {
        Node node = new Node();
        node.val = val;
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
    }

    public void addAtIndex(int index, int val) {
        Node node = new Node();
        node.val = val;

        if (index < 0) {
            index = 0;
        }

        if (head == null) {
            if (index == 0) {
                head = node;
                tail = node;
            }
            return;
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            if (current == tail) {
                if (i == index - 1) {
                    // 放在队尾
                    node.prev = tail;
                    tail.next = node;
                    tail = node;
                }
                return;
            }
            current = current.next;
        }

        if (current == head) {
            head = node;
        } else {
            current.prev.next = node;
        }
        node.prev = current.prev;
        node.next = current;
        current.prev = node;
    }

    public void deleteAtIndex(int index) {

        if (head == null) {
            return;
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
            if (current == null) {
                return;
            }
        }

        if (current == head) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
        } else if (current == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            current.next.prev = current.prev;
            current.prev.next = current.next;
        }

    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(7);
        list.addAtHead(2);
        list.addAtHead(1);
        list.addAtIndex(3,0);
        list.deleteAtIndex(2);
        list.addAtHead(6);
        list.addAtTail(4);
        System.out.println(list.get(4));
        list.addAtHead(4);
        list.addAtIndex(5, 0);
    }

}
