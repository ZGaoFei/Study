package com.zgf.javalib.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class AlgorithmStudy01 {
    public static void main(String[] args) {

        String a = "hhlelo";
        findChar(a);

        findCar1(a);

        String b = "hello     world!";
        swapString(b);

        int[] c = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
        int search = search(c, 5);
        System.out.println("===search===" + search);

        int[][] d = new int[][]{{1, 2, 3}, {4, 5, 6}};
        int[][] transpose = transpose(d);
        for (int i = 0; i < transpose.length; i++) {
            for (int j = 0; j < transpose[0].length; j++) {
                System.out.println("==transpose===" + transpose[i][j]);
            }
        }
    }

    /**
     * 找到字符串中第一个唯一出现的字符，输出位置
     *
     * hello
     * 0
     */
    private static void findChar(String string) {
        List<Character> list = new ArrayList<>();
        char current;
        for (int i = 0; i < string.length(); i++) {
            current = string.charAt(i);
            if (!list.contains(current)) {
                list.add(current);
            } else {
                continue;
            }
            boolean a = false;
            for (int j = i + 1; j < string.length(); j++) {
                char charAt = string.charAt(j);
                if (current == charAt) {
                    a = true;
                    break;
                }
            }
            if (!a) {
                System.out.println("====000====" + i);
                break;
            }
        }
    }

    private static void findCar1(String string) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < string.length(); i++) {
            if (map.get(string.charAt(i)) == 1) {
                System.out.println("======111======" + i);
                break;
            }
        }
    }

    /**
     * 反转字符串
     * hello world
     * olleh dlrow
     */
    private static void swapString(String string) {
        StringBuffer buffer = new StringBuffer();
        int i = 0;
        int length = string.length();
        while (i < length) {
            int start = i;
            while (i < length && string.charAt(i) != ' ') {
                i ++;
            }
            for (int j = start; j < i; j++) {
                buffer.append(string.charAt(start + i - 1 - j));
            }
            while (i < length && string.charAt(i) == ' ') {
                i ++;
                buffer.append(' ');
            }
        }

        System.out.println("===buffer=====" + buffer.toString());
    }

    /**
     * 二分查找
     */
    private static int search(int[] a, int tagret) {
        int middle = 0;
        int left = 0;
        int right = a.length - 1;
        while (left <= right) {
            middle = (right - left) / 2 + left;
            if (a[middle] == tagret) {
                return middle;
            } else if (a[middle] < tagret) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }

    /**
     * 反转矩阵
     */
    private static int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int [][] result = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }
}
