package com.zgf.javalib.offer;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态规划
 */
class DynamicProgramming {

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums1 = new int[]{1, -2, -3, 10, -4, 7, 2, -5, 9};
        int[] nums2 = new int[]{1, 2, -3, 4, -1, 1, -3, 2};
        maxSubArray(nums1);

        maxSubArray1(nums1);
        maxSubArray1(nums2);
        maxSubArray2(nums2);

        int i = jumpFloor(7);
        System.out.println(i);
        int i1 = jumpFloor1(7);
        System.out.println(i1);
        int i2 = jumpFloor2(7);
        System.out.println(i2);
        int i3 = jumpFloor3(7);
        System.out.println(i3);

        int i4 = jumpFloorExpand(5);
        System.out.println(i4);
        int i5 = jumpFloorExpand2(5);
        System.out.println(i5);

        int i6 = rectCover(5);
        System.out.println(i6);

        int[] profitNums = new int[]{3, 5, 7, 1, 4, 6};
        int profit = maxProfit(profitNums);
        System.out.println("profit: " + profit);

        int[][] gridNums = new int[][]{
                {1, 3, 1},
                {2, 5, 1},
                {4, 2, 1},
                {5, 6, 7}
        };
        int maxValue = maxValue(gridNums);
        System.out.println("max value: " + maxValue);

        int longest = longestSubString("helloworld");
        int longest1 = longestSubString("abcabcbb");
        int longest2 = longestSubString("bbbbb");
        int longest3 = longestSubString("pwwkew");
        System.out.println("longest: " + longest);
        System.out.println("longest1: " + longest1);
        System.out.println("longest2: " + longest2);
        System.out.println("longest3: " + longest3);
    }

    /**
     * JZ42 连续子数组的最大和
     */
    private static void maxSubArray(int[] nums) {
        int pre = 0;
        int maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }

        System.out.println(maxAns);
    }

    /**
     * JZ85 连续子数组的最大和(二)
     */
    private static void maxSubArray1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int maxNum = dp[0];

        int left = 0;
        int right = 0;

        int resl = 0;
        int resr = 0;
        for (int i = 1; i < n; i++) {
            right++;

            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);

            if (dp[i - 1] + nums[i] < nums[i]) {
                left = right;
            }

            if (dp[i] > maxNum || dp[i] == maxNum && (right - left + 1) > (resr - resl + 1)) {
                maxNum = dp[i];
                resl = left;
                resr = right;
            }
        }

        for (int i = resl; i <= resr; i++) {
            System.out.print(nums[i] + ",");
        }
        System.out.println(" max num : " + maxNum);
    }

    private static void maxSubArray2(int[] nums) {
        int n = nums.length;
        int x = nums[0];
        int y = 0;
        int maxNum = x;

        int left = 0;
        int right = 0;

        int resl = 0;
        int resr = 0;
        for (int i = 1; i < n; i++) {
            right++;

            y = Math.max(x + nums[i], nums[i]);
            if (x + nums[i] < nums[i]) {
                left = right;
            }

            if (y > maxNum || y == maxNum && (right - left + 1) > (resr - resl + 1)) {
                maxNum = y;
                resl = left;
                resr = right;
            }

            x = y;
        }

        for (int i = resl; i <= resr; i++) {
            System.out.print(nums[i] + ",");
        }
        System.out.println(" max num : " + maxNum);
    }

    /**
     * JZ69 跳台阶
     * <p>
     * 一次跳一个台阶或者两个台阶
     * 符合斐波那契数列
     */
    private static int jumpFloor(int num) {
        if (num <= 1) {
            return 1;
        }
        return jumpFloor(num - 1) + jumpFloor(num - 2);
    }

    static int[] f = new int[50];

    private static int jumpFloor1(int num) {
        if (num <= 1) {
            return 1;
        }
        if (f[num] > 0) {
            return f[num];
        }
        return f[num] = jumpFloor1(num - 1) + jumpFloor1(num - 2);
    }

    private static int jumpFloor2(int num) {
        int[] dp = new int[50];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= num; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[num];
    }

    private static int jumpFloor3(int num) {
        int a = 1;
        int b = 1;
        int c = 1;

        for (int i = 2; i <= num; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * JZ71 跳台阶扩展问题
     * <p>
     * 每次可以调1~n阶台阶
     */
    private static int jumpFloorExpand(int n) {
        if (n <= 1 && n >= 0) {
            return 1;
        }
        return 2 << (n - 2);
    }

    private static int jumpFloorExpand2(int n) {
        if (n <= 1 && n >= 0) {
            return 1;
        }
        int a = 1;
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = 2 * a;
            a = res;
        }
        return res;
    }

    /**
     * JZ70 矩形覆盖
     */
    private static int rectCover(int n) {
        if (n <= 2) {
            return n;
        }
        int a = 1;
        int b = 2;
        int res = 0;
        for (int i = 3; i <= n; i++) {
            res = a + b;
            a = b;
            b = res;
        }
        return res;
    }

    /**
     * JZ63 买卖股票的最好时机(一)
     */
    private static int maxProfit(int[] nums) {
        int res = 0;
        if (nums == null || nums.length == 0) {
            return res;
        }
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            res = Math.max(res, nums[i] - min);
            System.out.println("min: " + min + "==res: " + res);
        }
        return res;
    }

    /**
     * JZ47 礼物的最大价值
     */
    private static int maxValue(int[][] nums) {
        int m = nums.length; // 行
        int n = nums[0].length; // 列
        // 第一列
        for (int i = 1; i < m; i++) {
            nums[i][0] += nums[i - 1][0];
        }
        // 第一行
        for (int i = 1; i < n; i++) {
            nums[0][i] += nums[0][i - 1];
        }
        // 遍历其他各个位置
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                nums[i][j] += Math.max(nums[i - 1][j], nums[i][j - 1]);
            }
        }
        return nums[m - 1][n - 1];
    }

    /**
     * JZ48 最长不含重复字符的子字符串
     */
    private static int longestSubString(String string) {
        if (string == null || string.length() == 0) {
            return 0;
        }
        int max = 0;
        // 双指针
        int n = 1;
        int flag = 1;
        int m = string.length();
        // 记录是否重复的集合
        List<Character> list = new ArrayList<>();
        list.add(string.charAt(0));
        while (n < m) {
            char c = string.charAt(n);
            if (list.contains(c)) {
                max = Math.max(max, list.size());
                list.clear();
                // 从头开始
                flag++;
                n = flag;
            } else {
                list.add(c);
                n++;
            }
        }
        max = Math.max(max, list.size());
        return max;
    }

}
