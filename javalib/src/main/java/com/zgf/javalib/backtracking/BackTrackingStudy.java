package com.zgf.javalib.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 回溯算法
 */
public class BackTrackingStudy {

    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                {'a', 'b', 'c', 'e'},
                {'s', 'f', 'c', 's'},
                {'a', 'd', 'e', 'e'},
        };
        boolean abcc = hasPath(matrix, "abcd");
        System.out.println(abcc);
        boolean sfcc = hasPath(matrix, "sfcc");
        System.out.println(sfcc);

        int cal = cal(100);
        System.out.println(cal);

        int count = movingCount(1, 2, 3);
        System.out.println(count);
        int count1 = movingCount(0, 1, 3);
        System.out.println(count1);
        int count2 = movingCount(10, 1, 100);
        System.out.println(count2);
        int count3 = movingCount(5, 10, 100);
        System.out.println(count3);

        int[] nums = new int[]{1, 2, 3, 4};
        permute(nums);
    }

    /**
     * JZ12 矩阵中的路径
     */
    private static boolean hasPath(char[][] matrix, String word) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int n = matrix.length;
        int m = matrix[0].length;

        // 标志位，记录已经走过的，就不在走
        boolean[][] flag = new boolean[n][m];

        // 以每一个位置为起点，都走一遍
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dfs(matrix, n, m, i, j, word, 0, flag)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(char[][] matrix, int n, int m, int i, int j, String word, int k, boolean[][] flag) {
        if (i < 0 || i >= n || j < 0 || j >= m || (matrix[i][j] != word.charAt(k)) || flag[i][j]) {
            // 下标越界，字符串不匹配，已经访问过
            return false;
        }

        // 遍历完成
        if (k == word.length() - 1) {
            return true;
        }

        // 当前位置已经访问过
        flag[i][j] = true;
        // 以当前位置向上下左右四点走一遍，递归，直到越界或者满足条件
        if (dfs(matrix, n, m, i - 1, j, word, k + 1, flag)
                || dfs(matrix, n, m, i + 1, j, word, k + 1, flag)
                || dfs(matrix, n, m, i, j - 1, word, k + 1, flag)
                || dfs(matrix, n, m, i, j + 1, word, k + 1, flag)) {
            return true;
        }
        // 恢复当前位置，为下一次递归可以重新进入
        flag[i][j] = false;

        return false;
    }

    /**
     * JZ13 机器人的运动范围
     */
    private static int movingCount(int threshold, int rows, int cols) {
        if (rows <= 0 || cols <= 0 || threshold < 0) {
            return 0;
        }

        // 标志位，表示已经计算过
        boolean[][] flag = new boolean[rows][cols];
        int count = movingCountCore(threshold, rows, cols, 0, 0, flag);
        return count;
    }

    private static int movingCountCore(int threshold, int rows, int cols, int row, int col, boolean[][] flag) {
        // 边界，已经计算过的，不符合条件的返回
        if (row < 0 || row >= rows || col < 0 || col >= cols || flag[row][col] || (cal(row) + cal(col)) > threshold) {
            return 0;
        }

        // 标记已经计算过的
        flag[row][col] = true;
        // 以当前点向上下左右四点走一遍，直到不符合条件返回
        return 1 + movingCountCore(threshold, rows, cols, row - 1, col, flag)
                + movingCountCore(threshold, rows, cols, row + 1, col, flag)
                + movingCountCore(threshold, rows, cols, row, col - 1, flag)
                + movingCountCore(threshold, rows, cols, row, col + 1, flag);
    }

    /**
     * 计算数位之和
     */
    private static int cal(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    /**
     * BM55 没有重复项数字的全排列
     */
    private static void permute(int[] nums) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        backTrack(res, nums, list);

        for (int i = 0; i < res.size(); i++) {
            ArrayList<Integer> integers = res.get(i);
            System.out.println(integers.toString());
        }
    }

    private static void backTrack(ArrayList<ArrayList<Integer>> res, int[] nums, LinkedList<Integer> list) {
        // 如果list的长度与数组长度相等表示遍历一遍符合条件
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        // 以每一个位置为起点，遍历
        for (int i = 0; i < nums.length; i++) {
            // 如果已经遍历过了，则跳过
            if (list.contains(nums[i])) {
                continue;
            }
            // 添加进来没有访问过的
            list.add(nums[i]);
            // 以当前位置递归，找符合条件的组合
            backTrack(res, nums, list);
            // 回溯
            list.removeLast();
        }
    }
}
