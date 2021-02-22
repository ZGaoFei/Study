package com.zgf.javalib.algorithm;

// 链表
public class NodeAlgorithm<T> {
    private Node<T> header;
    private Node<T> tail;

    public NodeAlgorithm() {
        header = null;
        tail = null;
    }

    // 获取第一个节点
    public Node<T> getFirst() {
        return header;
    }

    // 获取最后一个节点
    public Node<T> getLast() {
        return tail;
    }

    // 创建第一个节点
    public void newFirstNode(T data) {
        header = new Node<>(data);

        tail = header;
    }

    // 添加节点
    public void addNode(T data) {
        tail.next = new Node<>(data);
        tail = tail.next;
    }

    // 输出链表
    public void printNodes() {
        Node<T> current = header;
        while (current != null) {
            System.out.println("==print==" + current.element);
            current = current.next;
        }
    }

    // 倒序输出链表
    public void printNodesRev(Node<T> header) {
        if (header != null) {
            printNodesRev(header.next);
            System.out.println("==rev print==" + header.element);
        }
    }

    // 链表反转
    public Node<T> revList(Node<T> header) {
        if (header == null) {
            return null;
        }

        Node<T> result = null;
        Node<T> pre = null;
        Node<T> current = header;

        while (current != null) {
            Node<T> next = current.next;

            if (next == null) {
                result = current;
            }

            current.next = pre;
            pre = current;
            current = next;
        }
        return result;
    }

    public static class Node<T> {
        public T element;
        public Node<T> next;

        public Node(T element) {
            this.element = element;
            next = null;
        }
    }
}
