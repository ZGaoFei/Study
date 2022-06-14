package com.zgf.javalib.offer;

class Study607 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 4, 4, 5, 6};
        getNumberOfK(nums, 4);

        int[][] nums2 = new int[][]{
                {1, 3, 5},
                {2, 4, 6},
                {7, 8, 9}};
        boolean b = find(nums2, 9);
        System.out.println(b);
    }

    /**
     * JZ53 数字在升序数组中出现的次数
     */
    private static void getNumberOfK(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        int m = 0;
        while (left <= right && left < nums.length - 1 && right >= 0) {
            if (nums[left] < k) {
                left++;
            }
            if (nums[right] > k) {
                right--;
            }
            if (nums[left] == k && nums[right] == k) {
                m = right - left + 1;
                break;
            }
            System.out.println(left + "====" + right);
        }
        System.out.println(m);
    }

    /**
     * JZ4 二维数组中的查找
     */
    private static boolean find(int[][] nums, int target) {
        if (nums.length == 0) {
            return false;
        }
        int n = nums.length;
        if (nums[0].length == 0) {
            return false;
        }
        int m = nums[0].length;
        for (int i = n - 1, j = 0; i >= 0 && j < m; ) {
            if (nums[i][j] > target) {
                i--;
            } else if (nums[i][j] < target) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }
}
