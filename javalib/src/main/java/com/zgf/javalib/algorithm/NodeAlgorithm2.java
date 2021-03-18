package com.zgf.javalib.algorithm;

class NodeAlgorithm2 {
    public static void main(String[] args) {
        Node<Integer> node = createNode(7);
        printNode(node);

//        Node<Integer> deleteNodeNData = deleteNodeNData(node, 1);
//        printNode(deleteNodeNData);

        Node<Integer> node1 = deleteNodeNData2(node, 4);
        printNode(node1);
    }

    private static Node<Integer> createNode(int n) {
        Node<Integer> head = null;
        Node<Integer> tail = null;
        for (int i = 0; i < n; i++) {
            if (head == null) {
                head = new Node<>(i + 1);
                tail = head;
            } else {
                tail.next = new Node<>(i + 1);
                tail = tail.next;
            }
        }
        return head;
    }

    private static void printNode(Node<Integer> node) {
        Node<Integer> head = node;
        while (head != null) {
            int val = head.val;
            System.out.print(val + "  ");
            head = head.next;
        }
        System.out.println();
    }

    /**
     * 删除链表中倒数的第n个数，然后返回链表的头
     *
     * node = 1 2 3 4 5 6
     * n = 3
     * node = 1 2 3 5 6
     */
    private static Node<Integer> deleteNodeNData(Node<Integer> node, int n) {
        Node<Integer> first = node;
        Node<Integer> tail = null;
        int i = 0;
        while (first != null) {
            first = first.next;
            if (i >= n) {
                if (tail == null) {
                    tail = node;
                } else {
                    tail = tail.next;
                }
            }
            i ++;
        }
        Node<Integer> result;
        if (tail == null) {
            result = node.next;
            node.next = null;
        } else {
            Node<Integer> node1 = tail.next;
            tail.next = node1.next;
            node1.next = null;
            result = node;
        }
        return result;
    }

    private static Node<Integer> deleteNodeNData2(Node<Integer> node, int n) {
        // 创建一个链表头结点的前的一个节点
        Node<Integer> dummy = new Node(0, node);
        Node<Integer> first = node;
        Node<Integer> tail = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        // 当first指到最后一个的时候，tail指到倒数第n+1的位置
        while (first != null) {
            first = first.next;
            tail = tail.next;
        }

        System.out.println(tail.val);
        // 需要删除节点的前一个的next指向要删除的节点的next
        tail.next = tail.next.next;
        Node<Integer> ans = dummy.next;

        return ans;
    }
}
