package com.zgf.javalib.algorithm.first;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Study330 {

    public static void main(String[] args) {
//        List<String> list1 = generateParenthesis(1);
//        Utils.printArrayList(list1);
//        System.out.println();
//        List<String> list2 = generateParenthesis(2);
//        Utils.printArrayList(list2);
//        System.out.println();
//        List<String> list3 = generateParenthesis(3);
//        Utils.printArrayList(list3);
//        System.out.println();
//        List<String> list4 = generateParenthesis(4);
//        Utils.printArrayList(list4);
//        System.out.println();

//        List<String> list5 = generateParenthesis1(1);
//        Utils.printArrayList(list5);
//        System.out.println();
//        List<String> list6 = generateParenthesis1(2);
//        Utils.printArrayList(list6);
//        System.out.println();
//        List<String> list7 = generateParenthesis1(3);
//        Utils.printArrayList(list7);
//        System.out.println();
//        List<String> list8 = generateParenthesis1(4);
//        Utils.printArrayList(list8);
//        System.out.println();

//        Node<Integer> n0 = new Node<>(1);
//        n0.next = new Node<>(2);
//        n0.next.next = new Node<>(3);
//        n0.next.next.next = new Node<>(4);
//        n0.next.next.next.next = new Node<>(5);
//        swapPairs(n0);

        int[] nums = {0, 1, 1, 2, 3, 3, 4, 4, 5, 6};
//        Utils.printArray(nums);
//        remove(nums);
//        Utils.printArray(nums);

//        int index = indexOf("hello world", "ll");
//        System.out.println(index);
//        int index1 = indexOf("hello world", "ld");
//        System.out.println(index1);
//        int index2 = indexOf("hello world", "ha");
//        System.out.println(index2);
//        int index3 = indexOf("hello world", "hello");
//        System.out.println(index3);
//        int index4 = indexOf("aabaabaafa", "aabaaf");
//        System.out.println(index4);

        int[] nums0 = {3, 2, 2, 3};
        removeTarget(nums0, 3);
        System.out.println();
        int[] nums1 = {0, 1, 2, 2, 3, 0, 4, 2};
        removeTarget(nums1, 2);
    }

    /**
     * 22 括号生成
     * <p>
     * 生成所有有效的括号组合
     * 类似于树的深度优先遍历
     */
    private static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }

        dfs("", n, n, res);
        return res;
    }

    private static void dfs(String curStr, int left, int right, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }
        if (left > right) {
            return;
        }
        if (left > 0) {
            dfs(curStr + "(", left - 1, right, res);
        }
        if (right > 0) {
            dfs(curStr + ")", left, right - 1, res);
        }
    }

    /**
     * 22
     * <p>
     * 树的广度优先算法
     */
    private static List<String> generateParenthesis1(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }

        Queue<CurrentNode> queue = new LinkedList<>();
        queue.offer(new CurrentNode("", n, n));

        while (!queue.isEmpty()) {
            CurrentNode curNode = queue.poll();
            if (curNode.left == 0 && curNode.right == 0) {
                res.add(curNode.res);
            }
            if (curNode.left > 0) {
                queue.offer(new CurrentNode(curNode.res + "(", curNode.left - 1, curNode.right));
            }
            if (curNode.right > 0 && curNode.left < curNode.right) {
                queue.offer(new CurrentNode(curNode.res + ")", curNode.left, curNode.right - 1));
            }
        }
        return res;
    }

    static class CurrentNode {
        private String res;
        private int left;
        private int right;

        public CurrentNode(String str, int left, int right) {
            this.res = str;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 24 两两交换链表中的节点
     */
    private static void swapPairs(Node node) {
        Node pre = new Node(0);
        pre.next = node;
        Node temp = pre;
        while (temp.next != null && temp.next.next != null) {
            Node start = temp.next;
            Node end = temp.next.next;
            temp.next = end;
            start.next = end.next;
            end.next = start;
            temp = start;
        }
        Utils.printlnNode(pre.next);
    }

    /**
     * 26 删除有序数组中的重复项
     * <p>
     * 双指针法
     */
    private static void remove(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return;
        }
        int fast = 1;
        int slow = 1;
        while (fast < length) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        System.out.println(slow);
    }

    /**
     * 27 移除元素
     * 给定一个数组和一个数，移除数组中所有与这个数相同的数
     * 仅使用O(1)的存储空间
     *
     * 快慢指针，同26题，后面不相同的元素覆盖前面相同的元素
     */
    private static void removeTarget(int[] nums, int target) {
        int length = nums.length;
        if (length == 0) {
            return;
        }
        int fast = 0;
        int slow = 0;
        while (fast < length) {
            if (nums[fast] != target) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        System.out.println(slow);
        Utils.printArray(nums);
    }

    /**
     * 28 实现strStr
     * <p>
     * 类似于Java中的indexOf
     *
     * 双指针，分别指向两个字符串，移动指针，找相同的字符
     * 记录第一个相同字符的位置，出现不相同的字符时，回退到第一个相同字符的下一个位置再次遍历
     */
    private static int indexOf(String haystack, String needle) {
        if (haystack == null || haystack.equals("")) {
            return 0;
        }
        if (needle == null || needle.equals("")) {
            return 0;
        }

        int hLength = haystack.length();
        int nLength = needle.length();
        // 遍历haystack字符串的下标
        int n = 0;
        // 遍历needle字符串的下标
        int start = 0;
        // 匹配的结束位置
        int index = -1;
        // 标志位，第一个相同的字符串的位置
        boolean tag = false;
        int first = 0;
        while (n < hLength) {
            char hC = haystack.charAt(n);
            char nC = needle.charAt(start);
            n++;
            if (hC == nC) {
                // 第一个相同的位置
                if (!tag) {
                    tag = true;
                    first = n; // n已经加过1了
                }
                start++;
                // 遍历完后面的字符串，返回
                if (start == nLength) {
                    index = n;
                    break;
                }
            } else if (tag) {
                tag = false;

                // 回退位置
                start = 0;
                n = first;
            }
        }
        return index == -1 ? -1 : index - nLength;
    }
}
