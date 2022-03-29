package com.zgf.javalib.algorithm.first;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Study329 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 0, -1, -4, -3, 2, 1};
        // fourNumSum(nums, 0);

        int[] nums1 = {1, 0, -1, 0, -2, 2};
        // fourNumSum(nums1, 0);

        int[] nums2 = {2, 2, 2, 2, 2};
        // fourNumSum(nums2, 8);

        Node<Integer> node0 = new Node<>(1);
        node0.next = new Node<>(2);
        node0.next.next = new Node<>(3);
        node0.next.next.next = new Node<>(4);
        node0.next.next.next.next = new Node<>(5);
//        removeNthFromEnd(node0, 5);
        System.out.println();
//        removeNthFromEnd1(node0, 5);
//        removeNthFromEnd2(node0, 5);

//        System.out.println("====");
//        String s = "()";
//        isValid(s);
//        isValid1(s);
//        System.out.println("====");
//        String s1 = "()[]{}";
//        isValid(s1);
//        isValid1(s1);
//        System.out.println("====");
//        String s2 = "(]";
//        isValid(s2);
//        isValid1(s2);
//        System.out.println("====");
//        String s3 = "([)]";
//        isValid(s3);
//        isValid1(s3);
//        System.out.println("====");
//        String s4 = "{[]}";
//        isValid(s4);
//        isValid1(s4);

        Node<Integer> n0 = new Node<>(1);
        n0.next = new Node<>(2);
        n0.next.next = new Node<>(4);
        Node<Integer> n1 = new Node<>(1);
        n1.next = new Node<>(3);
        n1.next.next = new Node<>(4);
        merge(n0, n1);


    }

    /**
     * 18 四数之和
     */
    private static void fourNumSum(int[] nums, int target) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            for (int j = i + 1; j < length - 2; j++) {
                for (int k = j + 1; k < length - 1; k++) {
                    for (int l = k + 1; l < length; l++) {
                        int sum = nums[i] + nums[j] + nums[k] + nums[l];
                        if (sum == target) {
                            builder.append("[")
                                    .append(nums[i]).append(",")
                                    .append(nums[j]).append(",")
                                    .append(nums[k]).append(",")
                                    .append(nums[l]).append(",")
                                    .append("]");
                            break;
                        }
                    }
                }
            }
        }
        builder.append("]");
        System.out.println(builder.toString());
    }

    /**
     * 19 删除链表的倒数第N个节点
     * <p>
     * 单指针方式
     * 先找到链表的长度
     * 单指针指向链表的第 length - n - 1 即要删除的位置的前一个位置
     * 执行删除操作，边界处理
     */
    private static void removeNthFromEnd(Node head, int n) {
        int length = 0;
        Node first = head;
        // 计算链表的长度
        while (first != null) {
            first = first.next;
            length++;
        }
        System.out.println(length);

        int start = 0;
        Node h = head;
        // 判断边界
        while (h != null && n < length) {
            // 找到要删除的节点的前一个位置
            if (start == length - n - 1) {
                break;
            }
            h = h.next;
            start++;
        }
        System.out.println(start + "====" + h.val);
        // 边界处理
        if (start == 0) {
            head = h.next;
        } else { // 删除节点
            Node next = h.next;
            h.next = next.next;
        }
        Utils.printlnNode(head);
    }

    private static int getLength(Node head) {
        Node first = head;
        int length = 0;
        // 计算链表的长度
        while (first != null) {
            first = first.next;
            length++;
        }
        return length;
    }

    private static void removeNthFromEnd1(Node head, int n) {
        Node first = new Node(0, head);
        int length = getLength(head);
        Node cur = first;
        for (int i = 1; i < length - n + 1; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        Utils.printlnNode(first.next);
    }

    /**
     * 19 删除链表的倒数第N个节点
     *
     * 双指针法
     */
    private static void removeNthFromEnd2(Node head, int n) {
        Node start = new Node(0, head);
        Node first = head;
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        Node second = start;
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        Utils.printlnNode(start.next);
    }

    /**
     * 20 有效的括号
     */
    private static void isValid(String s) {
        Stack<Character> stack = new Stack<>();
        boolean isValid = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // System.out.println("c: " + c);
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else {
                Character pop = stack.pop();
                // System.out.println("pop: " + pop);
                if (pop != c) {
                    isValid = false;
                    break;
                }
            }
        }
        System.out.println(isValid);
    }

    /**
     * 20 有效的括号
     * 利用栈的特性
     */
    private static void isValid1(String s) {
        int length = s.length();
        if (length % 2 == 1) {
            return;
        }
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();
        boolean isValid = true;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != map.get(c)) {
                    isValid = false;
                    break;
                }
            } else {
                stack.push(c);
            }
        }
        System.out.println(isValid);
    }

    /**
     * 21 合并两个有序链表
     *
     * 迭代法
     */
    private static void merge(Node<Integer> n0, Node<Integer> n1) {
        Node<Integer> node = new Node<>(-1);
        Node<Integer> first = node;
        while (n0 != null && n1 != null) {
            System.out.println(n0.val + "====" + n1.val);
            if (n0.val < n1.val) {
                node.next = n0;
                n0 = n0.next;
            } else {
                node.next = n1;
                n1 = n1.next;
            }
            node = node.next;
        }
        node.next = n0 == null ? n1: n0;

        Utils.printlnNode(first.next);
    }
}
