package com.zgf.javalib.algorithm.first;

public class Utils {

    // 遍历链表
    public static void printlnNode(Node node) {
        Node header = node;
        while (header != null) {
            System.out.print(header.val + " ");
            header = header.next;
        }
    }

    // 遍历数组
    public static void printArray(int[] num) {
        for (int i = 0; i < num.length; i++) {
            System.out.print(num[i] + " ");
        }
    }

    public static void printArray(String[] num) {
        for (int i = 0; i < num.length; i++) {
            System.out.print(num[i] + " ");
        }
    }
}
