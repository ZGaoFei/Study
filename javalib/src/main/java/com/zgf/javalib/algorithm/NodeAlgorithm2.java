package com.zgf.javalib.algorithm;

class NodeAlgorithm2 {
    public static void main(String[] args) {
        Node<Integer> node = createNode(7);
        printNode(node);

//        Node<Integer> deleteNodeNData = deleteNodeNData(node, 1);
//        printNode(deleteNodeNData);

        Node<Integer> node1 = deleteNodeNData2(node, 4);
        printNode(node1);

        System.out.println("========================");
        Node<Integer> node2 = createNode(7);
        printNode(node2);
        int data = 6;
        System.out.println("======" + data);
        Node<Integer> deleteNodeWithData = deleteNodeWithData(node2, data);
        printNode(deleteNodeWithData);

        Node<Integer> node4 = createNode(7);
        deleteNodeWithData2(node4, data);
        printNode(node4);
        System.out.println("========================");

        Node<Integer> node5 = createNode(0, 1, 1, 2, 2, 3, 3, 3, 4);
        printNode(node5);
        deleteNodeRepeatData(node5);
        printNode(node5);
        System.out.println("========================");

        Node<Integer> node6 = createNode(5);
        printNode(node6);
        int num = 16;
        System.out.println("====" + num);
        Node<Integer> spinNodeLeft = spinNodeLeft(node6, num);
        printNode(spinNodeLeft);
        System.out.println("===========left=============");

        Node<Integer> node7 = createNode(5);
        printNode(node7);
        int numRight = 16;
        System.out.println("====" + numRight);
        Node<Integer> spinNodeRight = spinNodeRight(node7, numRight);
        printNode(spinNodeRight);
        System.out.println("============right============");

        Node<Integer> node8 = createNode(5);
        printNode(node8);
        int numRight2 = 6;
        System.out.println("====" + numRight2);
        Node<Integer> spinNodeRight2 = spinNodeRight2(node8, numRight2);
        printNode(spinNodeRight2);
        System.out.println("============right2============");

        Node<Integer> node9 = createNode(4);
        printNode(node9);
        Node<Integer> swapAdjacentNode = swapAdjacentNode(node9);
        printNode(swapAdjacentNode);
        System.out.println("============swapAdjacentNode============");

        Node<Integer> node10 = createNode(5);
        printNode(node10);
        Node<Integer> swapNode = swapNode(node10);
        printNode(swapNode);
        System.out.println("============swapNode============");

        Node<Integer> node11 = createNode(5);
        printNode(node11);
        Node<Integer> swapRangeNode = swapRangeNode(node11, 3, 4);
        printNode(swapRangeNode);
        System.out.println("============swapRangeNode============");
    }

    private static Node<Integer> createNode(int... datas) {
        Node<Integer> head = null;
        Node<Integer> tail = null;
        for (int i = 0; i < datas.length; i++) {
            int data = datas[i];
            if (head == null) {
                head = new Node<>(data);
                tail = head;
            } else {
                tail.next = new Node<>(data);
                tail = tail.next;
            }
        }
        return head;
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
     * <p>
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
            i++;
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

    /**
     * 删除链表里面的某个元素，并返回剩余的链表
     * <p>
     * [1, 2, 3, 4, 5]
     * 4
     * [1, 2, 3, 5]
     */
    private static Node<Integer> deleteNodeWithData(Node<Integer> node, int data) {
        Node<Integer> dummy = new Node<>(0, node);
        Node<Integer> head = dummy;
        Node<Integer> tail = node;
        while (tail != null) {
            if (tail.val == data) {
                head.next = tail.next;
                tail.next = null;
                break;
            }
            tail = tail.next;
            head = head.next;
        }
        return dummy.next;
    }

    private static Node<Integer> deleteNodeWithData2(Node<Integer> node, int data) {
        Node<Integer> head = node;
        while (head != null) {
            if (head.val == data) {
                head.val = head.next.val;
                head.next = head.next.next;
                break;
            }
            head = head.next;
        }
        return node;
    }

    private static void deleteNode(Node node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 删除链表中的重复元素
     * <p>
     * 1, 2, 2, 3, 3, 3, 4
     * 1, 2, 3, 4
     */
    private static Node<Integer> deleteNodeRepeatData(Node<Integer> node) {
        Node<Integer> head = node;
        while (head != null && head.next != null) {
            if (head.val == head.next.val) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }
        return node;
    }

    /**
     * 向左旋转链表
     * 1, 2, 3, 4, 5, 6, null
     * 2
     * 3, 4, 5, 6, 1, 2, null
     */
    private static Node<Integer> spinNodeLeft(Node<Integer> node, int n) {
        Node<Integer> end = node;
        Node<Integer> tail = node;
        int length = 1;
        while (end.next != null) {
            end = end.next;
            length++;
        }
        n = n % length;
        while (n > 1) {
            if (tail.next == null) {
                tail = node;
            } else {
                tail = tail.next;
            }
            n--;
        }

        end.next = node;
        Node<Integer> result = tail.next;
        tail.next = null;

        System.out.println("========tail======" + tail.val + "====" + end.val + "====" + node.val);
        return result;
    }

    /**
     * 1, 2, 3, 4, 5, 6, null
     * 2
     * 5, 6, 1, 2, 3, 4, null
     * <p>
     * n超过5还不支持
     */
    private static Node<Integer> spinNodeRight(Node<Integer> node, int n) {
        Node<Integer> end = node;
        Node<Integer> dummy = node;
        int length = 1;
        while (end.next != null) {
            end = end.next;
            length++;
        }
        n = n % length;
        n = length - n;
        while (n > 1) {
            if (dummy.next == null) {
                dummy = node;
            } else {
                dummy = dummy.next;
            }
            n--;
        }

        end.next = node;
        Node<Integer> result = dummy.next;
        dummy.next = null;

        return result;
    }

    /**
     * 1, 2, 3, 4, 5
     * 1
     * 5, 1, 2, 3, 4
     */
    private static Node<Integer> spinNodeRight2(Node<Integer> node, int n) {
        Node<Integer> head = node;
        Node<Integer> tail = node;
        int length;
        for (length = 1; tail.next != null; length++) {
            tail = tail.next;
        }

        // 形成一个循环单向链表
        tail.next = head;

        n = n % length;
        n = length - n;

        // 找到新链表尾部的前一个位置的节点
        while (n > 1) {
            head = head.next;
            n--;
        }

        Node<Integer> newHead = head.next;
        head.next = null;

        return newHead;
    }

    /**
     * 交换链表中相邻的两个节点
     * 1, 2, 3, 4
     * 2, 1, 4, 3
     */
    private static Node<Integer> swapAdjacentNode(Node<Integer> node) {
        Node<Integer> head = new Node<>(0, node);
        Node<Integer> newNode = head;
        while (newNode.next != null && newNode.next.next != null) {
            Node<Integer> first = newNode.next;
            Node<Integer> second = first.next;

            newNode.next = second;
            first.next = second.next;
            second.next = first;

            newNode = first;
        }

        return head.next;
    }

    /**
     * 反转链表
     * 1, 2, 3, 4, 5
     * 5, 4, 3, 2, 1
     */
    private static Node<Integer> swapNode(Node<Integer> node) {
        Node<Integer> prev = null;
        Node<Integer> current = node;
        while (current != null) {
            // 首先记录下一个的节点
            Node<Integer> next = current.next;

            // 将当前节点的下一个节点指向前一个节点
            current.next = prev;
            // 指针后移
            prev = current;
            current = next;
        }

        return prev;
    }

    /**
     * 反转链表中一定范围的节点
     * 1, 2, 3, 4, 5
     * 2, 3
     * 1, 3, 2, 4, 5
     */
    private static Node<Integer> swapRangeNode(Node<Integer> node, int m, int n) {
        System.out.println("=====" + m + "====" + n);
        

        return node;
    }

}
