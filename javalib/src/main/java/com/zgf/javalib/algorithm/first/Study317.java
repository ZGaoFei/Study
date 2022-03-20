package com.zgf.javalib.algorithm.first;

import java.util.HashMap;
import java.util.Map;

// 22-3-17
class Study317 {
    public static void main(String[] args) {

        /*
        String[] words0 = new String[]{"w","wo","wor","worl", "world"};
        longestWord(words0);
        String[] words1 = new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"};
        longestWord(words1);

        int[] nums0 = new int[]{2,7,11,15};
        twoSum(nums0, 9);
        twoSum1(nums0, 9);
        int[] nums1 = new int[]{3,2,4};
        twoSum(nums1, 6);
        twoSum1(nums1, 6);
        int[] nums2 = new int[]{3,3};
        twoSum(nums2, 6);
        twoSum1(nums2, 6);
         */

        Node<Integer> node0 = new Node<>(2);
        node0.next = new Node<>(4);
        node0.next.next = new Node<>(3);
        Node<Integer> node1 = new Node<>(5);
        node1.next = new Node<>(6);
        node1.next.next = new Node<>(4);
        printlnNode(node0);
        System.out.println();
        printlnNode(node1);
        System.out.println();
        addTwoNums(node0, node1);
        System.out.println();

        Node<Integer> node2 = new Node<>(0);
        Node<Integer> node3 = new Node<>(0);
        addTwoNums(node2, node3);
        System.out.println();

        Node<Integer> node4 = new Node<>(9);
        node4.next = new Node<>(9);
        node4.next.next = new Node<>(9);
        node4.next.next.next = new Node<>(9);
        node4.next.next.next.next = new Node<>(9);
        node4.next.next.next.next.next = new Node<>(9);
        node4.next.next.next.next.next.next = new Node<>(9);

        Node<Integer> node5 = new Node<>(9);
        node5.next = new Node<>(9);
        node5.next.next = new Node<>(9);
        node5.next.next.next = new Node<>(9);
        printlnNode(node4);
        System.out.println();
        printlnNode(node5);
        System.out.println();
        addTwoNums(node4, node5);
    }

    /**
     * 720：词典中最长的单词
     *
     * 1、每一次遍历比较出每个字符串与其他字符串的包含关系，并记录
     * 2、只记录当前最大的包含关系次数和当前的index
     * 3、如果相同则比较字典顺序
     */
    private static void longestWord(String[] words) {
        int index = -1;
        int n = 0;
        for (int i = 0; i < words.length; i++) {
            int m = 0;
            for (int j = 0; j < words.length; j++) {
                if (i != j && words[i].contains(words[j])) {
                    m ++;
                }
            }
            if (m > n) {
                n = m;
                index = i;
            } else if (m == n && m > 0) {
                if (words[index].compareTo(words[i]) > 0) {
                    index = i;
                }
            }
        }
        String result = "";
        if (index >= 0) {
            result = words[index];
        }
        System.out.println("longest word is: " + result);
    }

    /**
     * 1、两数之和
     *
     */
    private static void twoSum(int[] nums, int target) {
        int a = -1;
        int b = -1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] + nums[j] == target) {
                    a = j;
                    b = i;
                }
            }
        }
        System.out.println("two num is: [" + a + "," + b + "]");
    }

    /**
     * 1、两数之和--进阶
     */
    private static void twoSum1(int[] nums, int target) {
        int a = -1;
        int b = -1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                a = map.get(target - nums[i]);
                b = i;
                break;
            } else {
                map.put(nums[i], i);
            }
        }

        System.out.println("two num is: [" + a + "," + b + "]");
    }

    /**
     * 2、两数相加
     *
     * 1、倒叙相加，进位加一
     *
     */
    private static void addTwoNums(Node<Integer> n1, Node<Integer> n2) {
        Node<Integer> node = new Node<>(0);
        Node<Integer> header = node;
        int i = 0;
        while (n1 != null || n2 != null) {
            int value1 = n1 == null ? 0 : n1.val;
            int value2 = n2 == null ? 0 : n2.val;
            int value = value1 + value2 + i;

            i = value / 10;
            value = value % 10;

            header.next = new Node<>(value);
            header = header.next;

            if (n1 != null) {
                n1 = n1.next;
            }
            if (n2 != null) {
                n2 = n2.next;
            }
        }
        if (i == 1) {
            header.next = new Node<>(1);
        }

        printlnNode(node.next);
    }

    private static void printlnNode(Node node) {
        Node header = node;
        while (header != null) {
            System.out.print(header.val + " ");
            header = header.next;
        }
    }

}
