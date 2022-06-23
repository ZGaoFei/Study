package com.zgf.javalib.offer;

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
     *
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
     *
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

}
