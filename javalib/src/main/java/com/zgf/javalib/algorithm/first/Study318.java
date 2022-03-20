package com.zgf.javalib.algorithm.first;

import java.util.HashMap;
import java.util.Map;

class Study318 {
    public static void main(String[] args) {

        // lengthOfLongestSubstring("abcabcab");
        // lengthOfLongestSubstring("bbbb");
        // lengthOfLongestSubstring("pwwkew");

        //                  1
        //          2              3
        //      4      5        9    0
        //               6
        //            7    8
        //
        //
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);
        treeNode.left.right.right = new TreeNode(6);
        treeNode.left.right.right.left = new TreeNode(7);
        treeNode.left.right.right.right = new TreeNode(8);
        treeNode.right.left = new TreeNode(9);
        treeNode.right.right = new TreeNode(0);
//        String result = tree2str(treeNode);
//        System.out.println(result);
//        String tree = printTree(treeNode);
//        System.out.println(tree);
//        String treeRight = printTreeRight(treeNode);
//        System.out.println(treeRight);


        int[] num1 = new int[] {1, 3, 5};
        int[] num2 = new int[] {2, 4};
        findMedianSortedArrays(num1, num2);
    }

    /**
     * 3 无重复字符的最长子串
     *
     * 滑动窗口法
     * start表示前面的指针位置
     * end表示后面的指针位置
     * 当后面指针向后移动，没有出现重复的字符串的话，就将出现过的字符串添加进入map里面，
     * 出现重复的字符串的话，将start的位置移动到end的位置，并记录最长路径
     * 依次类推，直到字符串遍历完成
     */
    private static void lengthOfLongestSubstring(String s) {
        int length = s.length();
        int ans = 0;
        int start = 0;
        int end;
        Map<Character, Integer> map = new HashMap<>();
        for (end = 0 ; end < length; end ++) {
            if (map.containsKey(s.charAt(end))) {
                start = Math.max(map.get(s.charAt(end)), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }

        System.out.println(ans);
    }

    /**
     * 606 根据二叉树创建字符串，前序遍历
     * 递归遍历二叉树
     */
    private static String tree2str(TreeNode treeNode) {
        if (treeNode == null) {
            return "";
        }
        if (treeNode.left == null && treeNode.right == null) {
            return treeNode.val + "";
        }
        if (treeNode.right == null) {
            return new StringBuffer()
                    .append(treeNode.val)
                    .append("(")
                    .append(tree2str(treeNode.left))
                    .append(")")
                    .toString();
        }
        return new StringBuffer()
                .append(treeNode.val)
                .append("(")
                .append(tree2str(treeNode.left))
                .append(")(")
                .append(tree2str(treeNode.right))
                .append(")")
                .toString();
    }

    /**
     * 遍历树
     * 左序遍历
     */
    private static String printTree(TreeNode treeNode) {
        if (treeNode == null) {
            return "";
        }
        if (treeNode.left == null && treeNode.right == null) {
            return treeNode.val + "";
        }
        if (treeNode.right == null) {
            return new StringBuffer()
                    .append(treeNode.val)
                    .append(printTree(treeNode.left))
                    .toString();
        }
        return new StringBuffer()
                .append(treeNode.val)
                .append(printTree(treeNode.left))
                .append(printTree(treeNode.right))
                .toString();
    }

    /**
     * 遍历树
     * 右序遍历
     */
    private static String printTreeRight(TreeNode treeNode) {
        if (treeNode == null) {
            return "";
        }
        if (treeNode.left == null && treeNode.right == null) {
            return treeNode.val + "";
        }
        if (treeNode.left == null) {
            return new StringBuffer()
                    .append(treeNode.val)
                    .append(printTreeRight(treeNode.right))
                    .toString();
        }
        return new StringBuffer()
                .append(treeNode.val)
                .append(printTreeRight(treeNode.right))
                .append(printTreeRight(treeNode.left))
                .toString();
    }

    /**
     * 4 寻找两个正序数组的中位数
     * 先合并数组
     * 用双指针法合并两个有序的数组
     */
    private static void findMedianSortedArrays(int[] num1, int[] num2) {
        int length1 = num1.length;
        int length2 = num2.length;
        int length = length1 + length2;
        int[] num = new int[length];
        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < length; i++) {
            System.out.println(index1 + " " + index2);
            if (index1 >= length1) {
                num[i] = num2[index2];
            } else if (index2 >= length2) {
                num[i] = num1[index1];
            } else if (num1[index1] > num2[index2]) {
                num[i] = num2[index2++];
            } else {
                num[i] = num1[index1++];
            }
        }
        Utils.printArray(num);

        System.out.println();
        int i = (length + 1) % 2;
        System.out.println(i);
        if (i == 0) {
            System.out.println(num[length / 2]);
        } else {
            int i1 = length / 2;
            System.out.println((num[i1] + num[i1 + 1]) / 2);
        }
    }

}
