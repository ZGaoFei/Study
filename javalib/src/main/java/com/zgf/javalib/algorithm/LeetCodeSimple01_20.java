package com.zgf.javalib.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        System.out.println("==========code=============");

        int[][] aa = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        for (int i = 0; i < aa.length; i++) {
            for (int j = 0; j < aa[i].length; j++) {
                System.out.print(aa[i][j] + "    ");
            }
            System.out.println();
        }
        int[] matrix1 = generateMatrix(aa);
        for (int i = 0; i < matrix1.length; i++) {
            System.out.print(matrix1[i] + "    ");
        }

        System.out.println("==========matrix=============");
        int[][] matrix = generateMatrix(4);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "    ");
            }
            System.out.println();
        }
        System.out.println("==========lengthOfLongestSubstring=============");
        String src = "abcdabcdaa";
        int s = lengthOfLongestSubstring(src);
        System.out.println("src: " + src + " s: " + s);
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

    /**
     * 两数相加
     * 两个链表，代表两个数字，并且是逆序连接，
     * 把两数相加后，逆序输出
     */
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

    /**
     * 给定一个矩阵，按顺时针旋转输出
     * <p>
     * 1  2  3
     * 4  5  6
     * 7  8  9
     * <p>
     * 1 2 3 6 9 8 7 4 5
     */
    private static int[] generateMatrix(int[][] matrix) {
        int length = matrix.length * matrix[0].length;
        int[] src = new int[length];
        int left = 0, up = 0, right = matrix.length - 1, down = matrix[0].length - 1;
        int index = 0;
        while (index < length) {
            for (int i = left; i <= right; i++) {
                src[index] = matrix[up][i];
                index++;
            }
            up++;

            for (int i = up; i <= down; i++) {
                src[index] = matrix[i][right];
                index++;
            }
            right--;

            for (int i = right; i >= left; i--) {
                src[index] = matrix[down][i];
                index++;
            }
            down--;

            for (int i = down; i >= up; i--) {
                src[index] = matrix[i][left];
                index++;
            }
            left++;
        }

        return src;
    }

    /**
     * 逆转矩阵
     * <p>
     * 3
     * <p>
     * 1  2  3
     * 8  9  4
     * 7  6  5
     */
    private static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int left = 0, up = 0, right = n - 1, down = n - 1;
        int index = 1;
        int a = n * n;
        while (index <= a) {
            for (int i = left; i <= right; i++) {
                // 从左向右，处理的是第up行的数据
                matrix[up][i] = index++;
            }
            up++;

            for (int i = up; i <= down; i++) {
                // 从上往下，处理的是第right列的数据
                matrix[i][right] = index++;
            }
            right--;

            for (int i = right; i >= left; i--) {
                // 从右向左，处理的是第down行数据
                matrix[down][i] = index++;
            }
            down--;

            for (int i = down; i >= up; i--) {
                // 从下往上，处理的是第left列的数据
                matrix[i][left] = index++;
            }
            left++;
        }

        return matrix;
    }

    /**
     * 字符串中的重复最长子串
     * <p>
     * abcdabca
     * <p>
     * abc = 3
     */
    private static int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }
}
