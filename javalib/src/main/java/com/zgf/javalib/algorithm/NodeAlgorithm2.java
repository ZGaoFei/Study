package com.zgf.javalib.algorithm;

import java.util.HashSet;

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
        Node<Integer> swapRangeNode = swapRangeNode(node11, 2, 4);
        printNode(swapRangeNode);
        System.out.println("============swapRangeNode=====1=======");

        Node<Integer> node12 = createNode(1, 2, 3, 4, 5, 6);
        Node<Integer> node13 = createNode(7, 6, 8, 0);
        printNode(node12);
        printNode(node13);
        findIntersectNode(node12, node13);
        System.out.println("============findIntersectNode============");

        Node<Integer> node14 = createNode(1, 2, 3, 4, 5, 6);
        Node<Integer> node15 = createNode(7, 6, 8, 0);
        printNode(node14);
        printNode(node15);
        Node<Integer> intersectNode2 = findIntersectNode2(node14, node15);
        System.out.println("============findIntersectNode==222==========" + intersectNode2.val);

        Node<Integer> node16 = createNode(1, 2, 3, 4, 5, 6);
        printNode(node16);
        Node<Integer> lastNode = getLastNode(node16);
        System.out.println("===lastNode====" + lastNode.val);
        Node<Integer> getNode = getNode(node16, 2);
        System.out.println("===getNode====" + getNode.val);
        lastNode.next = getNode;
        Node<Integer> detectCycle = detectCycle(node16);
        System.out.println("============detectCycle============" + detectCycle.val);

        Node<Integer> node17 = createNode(1, 2, 3, 4, 5, 6);
        printNode(node17);
        Node<Integer> lastNode17 = getLastNode(node17);
        System.out.println("===lastNode====" + lastNode17.val);
        Node<Integer> getNode17 = getNode(node17, 0);
        System.out.println("===getNode====" + getNode17.val);
        lastNode17.next = getNode17;
        Node<Integer> detectCycle2 = detectCycle2(node17);
        System.out.println("============detectCycle====222========" + detectCycle2.val);

        Node<Integer> node18 = createNode(3, 2, 1, 4, 6, 5);
        printNode(node18);
        Node<Integer> sortNode = sortNode(node18);
        printNode(sortNode);
        System.out.println("============sortNode============");
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

    private static Node<Integer> getLastNode(Node<Integer> node) {
        Node<Integer> head = node;
        while (head != null && head.next != null) {
            head = head.next;
        }
        return head;
    }

    private static Node<Integer> getNode(Node<Integer> node, int index) {
        Node<Integer> head = node;
        for (int i = 0; i < index && head != null; i++) {
            head = head.next;
        }
        return head;
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

    /**
     * 双指针
     *
     * 先让第一个指针走n步，然后两个指针一起走，
     * 当第一个指针走到尾部的时候，第二个指针刚好在倒数第n的位置
     *
     * 这里要找到倒数第n+1的位置，做删除操作
     */
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
     * <p>
     * 头插法
     */
    private static Node<Integer> swapRangeNode(Node<Integer> node, int m, int n) {
        Node<Integer> left = node;

        // 找到需要反转节点的前一个节点
        for (int i = 1; i < m - 1; i++) {
            left = left.next;
        }

        // 找到第一个要反转的节点
        Node<Integer> first = left.next;

//        for (int i = 0; i < n - m; i++) {
//            Node<Integer> next = first.next;
//            first.next = first.next.next;
//            next.next = left.next;
//            left.next = next;
//        }
        for (int i = 0; i < n - m; i++) {
            Node<Integer> next = first.next;
            first.next = next.next;
            next.next = left.next;
            left.next = next;
        }

        return node;
    }

    /**
     * 找两个链表相交的节点，并返回每个链表当前节点的前面有几个节点和当前相交的值
     * <p>
     * 1, 2, 3, 4, 5, 6
     * 7, 4, 8, 9, 0
     * <p>
     * 4
     * 3, 1
     */
    private static void findIntersectNode(Node<Integer> one, Node<Integer> two) {
        int o = 0, t = 0, v = -1;
        while (one != null) {
            Node<Integer> twoNode = two;
            t = 0;
            while (twoNode != null) {
                if (one.val.equals(twoNode.val)) {
                    v = one.val;
                    break;
                }
                twoNode = twoNode.next;
                t++;
            }
            if (v != -1) {
                break;
            }
            one = one.next;
            o++;
        }

        System.out.println("====v===" + v + "==o==" + o + "==t==" + t);
    }

    private static Node<Integer> findIntersectNode2(Node<Integer> headA, Node<Integer> headB) {
        if (headA == null || headB == null)
            return null;

        Node<Integer> pA = headA, pB = headB;
        while (pA.val != pB.val) {
            pA = pA.next == null ? headB : pA.next;
            pB = pB.next == null ? headA : pB.next;
        }

        return pA;
    }

    /**
     * 判断链表是否有环
     * <p>
     * 1, 2, 3, 4, 5, 2
     * 2
     * 利用HashSet来判断，HashSet不能保存相同的元素
     */
    private static Node<Integer> detectCycle(Node<Integer> node) {
        Node<Integer> head = node;
        HashSet<Node<Integer>> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return head;
            } else {
                set.add(head);
            }
            head = head.next;
        }
        return null;
    }

    /**
     * 判断链表是否有环
     *
     * 双指针法，快慢指针
     * <p>
     * 1、先快慢指针找到相交节点
     * 2、再用相同指针找到环的入口节点
     * <p>
     * 快指针fast和慢指针slow
     * 快指针每次走两步，慢指针每次走一步，因此fast = 2slow
     * 两个指针相遇的时候，快指针走了fast = slow + nb（b为环的一圈长度，n为圈数，因为相遇了，所以一定相等）
     * fast = 2slow -> fast = slow + nb -> slow = nb
     * 而环的入口节点是a + nb（a 为头结点到入口节点的距离，转了n圈之后肯定还是入口节点位置）
     * 从头结点走a步肯定就是入口节点，而slow已经走了nb步了，因此slow再走a步也刚好到入口节点
     * <p>
     * 相同指针
     * 两个指针同时从slow当前位置和头节点的位置走a步，相交的节点就是入口节点
     */
    private static Node<Integer> detectCycle2(Node<Integer> node) {
        Node<Integer> fast = node;
        Node<Integer> slow = node;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast.val == slow.val) {
                break;
            }
        }
        fast = node;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    /**
     * 排序链表
     *
     * 归并排序
     * <p>
     * 1, 3, 6, 2, 4, 5
     * 1, 2, 3, 4, 5, 6
     */
    private static Node<Integer> sortNode(Node<Integer> head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 快慢指针找到中间节点
        Node<Integer> fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 将链表分成两段
        Node<Integer> temp = slow.next;
        slow.next = null;

        // 对两段链表分别做递归操作，进行拆分
        Node<Integer> left = sortNode(head);
        Node<Integer> right = sortNode(temp);

        // 创建一个空的头结点，用于最后返回使用
        Node<Integer> h = new Node<>(0);
        Node<Integer> res = h;

        // 合并，用h将各个节点拼接起来
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        // 判断最后一个是否都已经拼接上
        h.next = left != null ? left : right;

        return res.next;
    }

}
