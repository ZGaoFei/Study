package com.zgf.javalib.offer;

public class Study614 {

    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3, 4, 4, 4, 5};
        int numberOfK = getNumberOfK(nums, 6);
        System.out.println(numberOfK);

        int[][] numss = new int[][] {
                {1, 2, 3, 4},
                {2, 3, 4, 5},
                {3, 4, 5, 6},
                {4, 5, 6, 7},
                {7, 8, 9, 10}
        };
        boolean b = find(numss, 5);
        System.out.println("find: " + b);
        boolean b1 = find1(numss, 5);
        System.out.println("find: " + b1);

        int[] minNums = new int[]{3, 4, 5, 3, 3};
        int i = minNumberInRotateArray(minNums);
        System.out.println("==i==" + i);
        int i1 = minNumberInRotateArray1(minNums);
        System.out.println("==i1==" + i1);
    }

    /**
     * JZ53 数字在升序数组中出现的次数
     */
    private static int getNumberOfK(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (nums[left] < k) {
                left++;
            }
            if (nums[right] > k) {
                right--;
            }

            if (right < 0 || left >= nums.length) {
                break;
            }
            if (nums[left] == k && nums[right] == k) {
                break;
            }
        }
        return right - left + 1;
    }

    /**
     * JZ4 二维数组中的查找
     */
    private static boolean find(int[][] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int row = nums.length;
        int clown = nums[0].length;
        int i = row - 1;
        int j = 0;
        while (i >= 0 && i < row && j < clown && j >= 0) {
            if (nums[i][j] == target) {
                return true;
            } else if (nums[i][j] < target) {
                j++;
            } else {
                i--;
            }
        }
        return false;
    }

    private static boolean find1(int[][] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int row = nums.length;
        int clown = nums[0].length;
        int i = 0;
        int j = 0;
        while (i >= 0 && i < row && j < clown && j >= 0) {
            if (nums[i][j] == target) {
                return true;
            } else if (nums[i][j] < target) {
                j++;
                if (j >= clown) {
                    i++;
                    j = 0;
                }
            } else {
                i++;
            }
        }
        return false;
    }

    /**
     * JZ11 旋转数组的最小数字
     *
     * 旋转数组局部升序
     * [3, 4, 5, 1, 2]
     *
     * 暴力法
     */
    private static int minNumberInRotateArray(int[] nums) {
        if (nums == null) {
            return -1;
        }
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min = Math.min(nums[i], min);
        }
        return min;
    }

    private static int minNumberInRotateArray1(int[] nums) {
        if (nums == null) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                right--;
            }
        }
        return nums[right];
    }

    /**
     * JZ38 字符串的排列
     *
     * 回溯算法
     */
    private static void permutation(String string) {
        if (string == null || string.length() == 0) {
            return;
        }
        char[] chars = string.toCharArray();
        int length = chars.length;

    }
}
