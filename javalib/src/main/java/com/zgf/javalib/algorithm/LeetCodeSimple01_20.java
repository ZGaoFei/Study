package com.zgf.javalib.algorithm;

import java.util.HashMap;
import java.util.Map;

// 力扣简单类型 1-20 题
class LeetCodeSimple01_20 {
    public static void main(String[] args) {
        int[] a = {1, 4, 6, 9, 0};
//        code01(a, 9);
//        code01_good(a, 10);

        ListNode l1 = new ListNode(5);
        ListNode l11 = new ListNode(7);
        ListNode l111 = new ListNode(6);
        l1.next = l11;
        l11.next = l111;

        ListNode l2 = new ListNode(2);
        ListNode l21 = new ListNode(5);
        ListNode l211 = new ListNode(3);
        l2.next = l21;
        l21.next = l211;

        code02(l1, l2);
        code02_good(l1, l2);

    }

    // 两数之和
    private static void code01(int[] a, int target) {
        int[] index = new int[2];
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] + a[j] == target) {
                    index[0] = i;
                    index[1] = j;
                    break;
                }
            }
        }

        for (int i = 0; i < index.length; i++) {
            System.out.println(index[i]);
        }
    }

    // 两数之和
    private static void code01_good(int[] a, int target) {
        int[] index = new int[2];
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < a.length; ++i) {
            if (hashMap.containsKey(target - a[i])) {
                index[0] = i;
                index[1] = hashMap.get(target - a[i]);
                break;
            }
            hashMap.put(a[i], i);
        }

        for (int i = 0; i < index.length; i++) {
            System.out.println(index[i]);
        }
    }

    // 两数相加
    private static void code02(ListNode l1, ListNode l2) {
        int num1 = code02_nodeToNum(l1);
        int num2 = code02_nodeToNum(l2);
        int num = num1 + num2;
        System.out.println(num);
        ListNode listNode = code02NumToNode(num);
        while (listNode != null) {
            System.out.println("=====" + listNode.val);
            listNode = listNode.next;
        }
    }

    private static int code02_nodeToNum(ListNode l) {
        int num = l.val;
        int i = 10;
        while (l.next != null) {
            ListNode next = l.next;
            num += (i * next.val);
            i *= 10;
            l = l.next;
        }
        System.out.println(num);
        return num;
    }

    private static ListNode code02NumToNode(int num) {
        String numStr = String.valueOf(num);
        ListNode listNode = new ListNode(Integer.parseInt(numStr.substring(numStr.length() - 1)));
        ListNode temp = listNode;
        for (int i = numStr.length() - 2; i >= 0; i--) {
            ListNode l = new ListNode(Integer.parseInt(numStr.substring(i, i + 1)));
            temp.next = l;
            temp = l;
        }

        return listNode;
    }

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

    private static void code02_good(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 == null ? 0 : l1.val;
            int n2 = l2 == null ? 0 : l2.val;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }

        while (head != null) {
            System.out.println("===code02_good==" + head.val);
            head = head.next;
        }
    }
}
