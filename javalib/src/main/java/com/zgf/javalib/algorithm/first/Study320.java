package com.zgf.javalib.algorithm.first;

import java.util.ArrayList;
import java.util.List;

class Study320 {
    public static void main(String[] args) {
//        longestPalindrome0("abcba");
//        longestPalindrome0("abcb");
//        longestPalindrome0("bb");
//        longestPalindrome0("bcbc");
//
//        longestPalindrome1("abcba");
//        longestPalindrome1("abcb");
//        longestPalindrome1("bb");
//        longestPalindrome1("bcbc");

        convert("abcdefghijk", 3);
        convert("abcdefghijk", 4);
        convert("abcdefghijk", 5);
    }

    /**
     * 5 最长回文子串
     * <p>
     * 暴力破解法
     * 找出所有的子串判断是否是回文子串，并保存最长的子串
     */
    private static void longestPalindrome0(String s) {
        int max = 0;
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String substring = s.substring(i, j);
                if (isPalindromic(substring)) {
                    if (max < substring.length()) {
                        max = substring.length();
                        result = substring;
                    }
                }
            }
        }
        System.out.println(result);
    }

    // 判断是否是回文子串
    private static boolean isPalindromic(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 5 最长回文子串
     * <p>
     * 中心扩展算法
     */
    private static void longestPalindrome1(String s) {
        if (s == null || s.length() < 1) {
            return;
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int i1 = expandAroundCenter(s, i, i);// 以一个字符为中心
            int i2 = expandAroundCenter(s, i, i + 1);// 以两个字符为中心
            int len = Math.max(i1, i2);
            System.out.println("i1: " + i1 + " i2: " + i2 + " len: " + len);
            if (len > end - start) {
                // 从子串的长度算出子串的开始和结束的位置
                start = i - (len - 1) / 2;
                end = i + len / 2;
                System.out.println("start: " + start + " end: " + end);
            }
        }

        System.out.println(s.substring(start, end + 1));
    }

    // 确定回文子串的长度
    private static int expandAroundCenter(String s, int left, int right) {
        // 确定左边界和右边界
        // 从中心向两边判断字符是否相等
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        System.out.println("left: " + left + " right: " + right);
        return right - left - 1;
    }

    /**
     * 6 Z字形变换
     *
     * 根据字符串取值下标的变化，从0 到 numrows-1 到 0
     * 每一行对应一个字符串，取出每个字符放在对应行里面
     * 然后依次输出
     */
    private static void convert(String s, int numRows) {
        if (s.length() < 2) {
            return;
        }
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            list.add(new StringBuilder());
        }
        int i = 0;
        int flag = -1;
        for (char c : s.toCharArray()) {
            list.get(i).append(c);
            // 根据位置反转
            if (i == 0 || i == numRows - 1) {
                flag = -flag;
            }
            i += flag;
        }

        String result = "";
        for (int j = 0; j < list.size(); j++) {
            result += list.get(j).toString();
        }
        System.out.println(result);
    }

    /**
     * 7 整数反转
     *
     */
    private static void reverse(int x) {

    }
}
