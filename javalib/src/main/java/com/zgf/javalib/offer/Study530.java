package com.zgf.javalib.offer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

class Study530 {

    public static void main(String[] args) {
        Node<Integer> node = new Node<>(1);
        node.next = new Node<>(2);
        node.next.next = new Node<>(3);
        node.next.next.next = new Node<>(4);
        // printListFromTailToHead(node);

        System.out.println();
        // reverse(node);

        Node<Integer> node1 = new Node<>(1);
        node1.next = new Node<>(3);
        node1.next.next = new Node<>(4);
        node1.next.next.next = new Node<>(7);

        Node<Integer> node2 = new Node<>(2);
        node2.next = new Node<>(5);
        node2.next.next = new Node<>(8);
        node2.next.next.next = new Node<>(9);
        // merge(node1, node2);

        Node<Integer> node3 = new Node<>(1);
        node3.next = new Node<>(3);
        node3.next.next = new Node<>(4);
        node3.next.next.next = new Node<>(7);

        Node<Integer> node4 = new Node<>(2);
        node4.next = new Node<>(4);
        node4.next.next = new Node<>(6);
        node4.next.next.next = new Node<>(7);
//        findFirstCommonNode(node3, node4);
//        findFirstCommonNode1(node3, node4);
//        findFirstCommonNode2(node3, node4);

        Node<Integer> node5 = new Node<>(2);
        Node<Integer> node6 = new Node<>(4);
        Node<Integer> node7 = new Node<>(6);
        Node<Integer> node8 = new Node<>(7);
        Node<Integer> node9 = new Node<>(8);
        Node<Integer> node10 = new Node<>(9);
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        node9.next = node7;
//        entryNodeOfLoop(node5);
//        entryNodeOfLoop1(node5);

//        findKthToTail(node, 3);
//        findKthToTail1(node, 3);

//        deleteNode(node, 2);

        Node<Integer> node11 = new Node<>(3);
        node11.next = new Node<>(4);
        node11.next.next = new Node<>(4);
        node11.next.next.next = new Node<>(5);
        node11.next.next.next.next = new Node<>(5);
//        deleteDuplication(node11);

        RandomNode<Integer> a = new RandomNode<>(1);
        RandomNode<Integer> b = new RandomNode<>(2);
        RandomNode<Integer> c = new RandomNode<>(3);
        RandomNode<Integer> d = new RandomNode<>(4);
        RandomNode<Integer> e = new RandomNode<>(5);
        a.next = b;
        a.random = c;

        b.next = c;
        b.random = e;

        c.next = d;

        d.next = e;
        d.random = b;

        cloneNode(a);
    }

    /**
     * 1、从尾到头打印链表
     *
     * @param node
     */
    private static void printListFromTailToHead(Node<Integer> node) {
        Node<Integer> first = node;
        Stack<Integer> stack = new Stack<>();
        while (first != null) {
            stack.push(first.value);
            first = first.next;
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + ",");
        }
    }

    /**
     * 2、反转链表
     * <p>
     * 利用栈或者其他有序集合，然后放入，后倒叙输出
     */
    private static void reverse(Node<Integer> node) {
        Node<Integer> first = null;
        while (node != null) {
            Node<Integer> next = node.next;
            node.next = first;
            first = node;
            node = next;

            System.out.println("==first==" + first.value);
            System.out.println("==node==" + node.value);
        }

        printlnNode(first);
    }

    /**
     * 3、合并两个排序的链表
     */
    private static void merge(Node<Integer> node1, Node<Integer> node2) {
        Node<Integer> first = new Node<>(-1);
        Node<Integer> header = first;
        while (node1 != null && node2 != null) {
            if (node1.value > node2.value) {
                first.next = node2;
                node2 = node2.next;
            } else {
                first.next = node1;
                node1 = node1.next;
            }
            first = first.next;
        }
        if (node1 != null) {
            first.next = node1;
        }
        if (node2 != null) {
            first.next = node2;
        }

        printlnNode(header.next);
    }

    /**
     * 4 两个链表的第一个公共结点
     * <p>
     * 遍历两个链表
     */
    private static void findFirstCommonNode(Node<Integer> node1, Node<Integer> node2) {
        Node<Integer> one = node1;
        Node<Integer> two = node2;
        Node<Integer> common = null;
        while (true) {
            if (one == null) {
                one = node1;
                two = two.next;
            }
            if (two == null) {
                break;
            }
            if (one.value.equals(two.value)) {
                common = one;
                break;
            } else {
                one = one.next;
            }
        }
        if (common == null) {
            System.out.println("{}");
        } else {
            System.out.println(common.value);
        }
    }

    /**
     * 4 两个链表的第一个公共结点
     */
    private static void findFirstCommonNode1(Node<Integer> node1, Node<Integer> node2) {
        Node<Integer> l1 = node1;
        Node<Integer> l2 = node2;
        while (!l1.value.equals(l2.value)) {
            l1 = (l1 == null) ? node2 : l1.next;
            l2 = (l2 == null) ? node1 : l2.next;
        }
        System.out.println("==l1==" + l1.value);
    }

    /**
     * 4 两个链表的第一个公共结点
      */
    private static void findFirstCommonNode2(Node<Integer> node1, Node<Integer> node2) {
        List<Integer> list = new ArrayList<>();
        while (node1 != null) {
            list.add(node1.value);
            node1 = node1.next;
        }
        Node<Integer> common = null;
        while (node2 != null) {
            if (list.contains(node2.value)) {
                common = node2;
                break;
            } else {
                node2 = node2.next;
            }
        }
        if (common == null) {
            System.out.println("{}");
        } else {
            System.out.println("====" + common.value);
        }
    }

    /**
     * 5 链表中环的入口结点
     *
     * hash法
     */
    private static void entryNodeOfLoop(Node<Integer> node) {
        Set<Integer> set = new HashSet<>();
        Node<Integer> common = null;
        while (node != null) {
            if (set.contains(node.value)) {
                common = node;
                break;
            } else {
                set.add(node.value);
                node = node.next;
            }
        }
        if (common == null) {
            System.out.println("{}");
        } else {
            System.out.println("==common==" + common.value);
        }
    }

    /**
     * 5 链表中环的入口结点
     *
     * 快慢指针法
     */
    private static void entryNodeOfLoop1(Node<Integer> node) {
        Node<Integer> fast = node;
        Node<Integer> low = node;
        while (fast != null && fast.next != null) {
            low = low.next;
            fast = fast.next.next;
            if (low == fast) {
                break;
            }
        }

        if (fast == null) {
            System.out.println("{}");
            return;
        }

        fast = node;
        while (!fast.value.equals(low.value)) {
            fast = fast.next;
            low = low.next;
        }

        System.out.println("==fast==" + fast.value);
    }

    /**
     * 6 链表中倒数第几个结点
     */
    private static void findKthToTail(Node<Integer> node, int target) {
        int n = -1;
        Node<Integer> first = node;
        while (first != null) {
            n ++;
            first = first.next;
        }
        for (int i = 0; i < n - target; i++) {
            node = node.next;
        }
        System.out.println(node.value);
    }

    /**
     * 6 链表中倒数第几个结点
     */
    private static void findKthToTail1(Node<Integer> node, int target) {
        Node<Integer> first = node;
        Node<Integer> two = node;
        for (int i = 0; i <= target; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            two = two.next;
        }
        System.out.println(two.value);
    }

    private static class RandomNode<T> {
        T value;
        RandomNode<T> next;
        RandomNode<T> random;

        public RandomNode(T t) {
            value = t;
        }
    }

    /**
     * JZ35 复杂链表的复制
     */
    private static void cloneNode(RandomNode<Integer> node) {
        if (node == null) {
            return;
        }
        RandomNode<Integer> header = node;
        Map<RandomNode<Integer>, RandomNode<Integer>> map = new HashMap<>();
        while (header != null) {
            map.put(header, new RandomNode<>(header.value));
            header = header.next;
        }
        header = node;
        while (header != null) {
            map.get(header).next = map.get(header.next);
            map.get(header).random = map.get(header.random);
            header = header.next;
        }

        RandomNode<Integer> res = map.get(node);
        RandomNode<Integer> first = res;
        while (first != null) {
            System.out.print(first.value + ",");
            first = first.next;
        }
        System.out.println();

        first = res;
        while (first != null) {
            if (first.random != null) {
                System.out.print(first.random.value + ",");
            }
            first = first.next;
        }
    }

    /**
     * JZ76 删除链表中重复的结点
     */
    private static void deleteDuplication(Node<Integer> node) {
        if (node == null) {
            return;
        }

        Node<Integer> header = new Node<>(0);
        header.next = node;
        Node<Integer> cur = header;

        while (cur.next != null && cur.next.next != null) {
            if (cur.next.value.equals(cur.next.next.value)) {
                int temp = cur.next.value;
                while (cur.next != null && cur.next.value.equals(temp)) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        printlnNode(header.next);
    }

    /**
     * JZ18 删除链表中的结点
     */
    private static void deleteNode(Node<Integer> node, int target) {
        Node<Integer> head = node;
        if (head.value.equals(target)) {
            printlnNode(head.next);
            return;
        }
        Node<Integer> first = head;
        while (head != null && head.next != null) {
            if (head.next.value.equals(target)) {
//                Node<Integer> temp = head.next.next;
//                head.next.next = null;
//                head.next = temp;

                head.next = head.next.next;
                printlnNode(first);
                break;
            } else {
                head = head.next;
            }
        }
    }

    private static <T> void printlnNode(Node<T> node) {
        Node<T> first = node;
        while (first != null) {
            System.out.print(first.value + ",");
            first = first.next;
        }
        System.out.println();
    }

}
