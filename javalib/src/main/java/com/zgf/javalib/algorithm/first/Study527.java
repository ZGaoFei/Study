package com.zgf.javalib.algorithm.first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

class Study527 {
    public static void main(String[] args) {
        int[] nums = new int[]{5, 2, 5, 0, 4, 6};
//        int i = firstMissingPositive(nums);
//        System.out.println(i);
//
//        int i1 = firstMissingPositive1(nums);
//        System.out.println(i1);

//        int trap = trap(nums);
//        System.out.println(trap);

//        int[] nums1 = new int[]{2,3,1,1,4};
//        jump(nums1);
//        jump1(nums1);

        int[] nums2 = new int[]{1, 1, 1};
        // permute(nums2);

//        permuteUnique(nums2);

        int[][] matrix = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        int[][] matrix1 = new int[][] {
                {5,1,9,11},
                {2,4,8,10},
                {13,3,6,7},
                {15,14,12,16}
        };
        //rotate(matrix);
        //rotate(matrix1);

        rotate1(matrix);
    }

    /**
     * 41 缺失的第一个正数
     *
     * 哈希表法
     */
    private static int firstMissingPositive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
        }

        for (int i = 0; i < n + 1; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return n + 1;
    }

    /**
     * 41 缺失的第一个正数
     *
     * 二分查找法
     */
    private static int firstMissingPositive1(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            if (binarySearch(nums, i) == -1) {
                return i;
            }
        }
        return n + 1;
    }

    private static int binarySearch(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 42 接雨水
     *
     * 看题说明
     */
    private static int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int ans = 0;
        int leftMax = 0;
        int rightMax = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    ans += (leftMax - height[left]);
                }
                ++left;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    ans += (rightMax - height[right]);
                }
                --right;
            }
        }
        return ans;
    }

    private static int sum(int[] nums, int start, int end) {
        int sum = nums[start] * (end - start - 1);
        System.out.println("==0000==" + sum);
        for (int i = start + 1; i < end; i++) {
            sum -= nums[i];
        }
        System.out.println("==1111==" + sum);
        return sum;
    }

    /**
     * 45 跳跃游戏II
     *
     * 引申为数组当前值所表示的下标范围内的最大值
     */
    private static void jump(int[] nums) {
        int c = 1;

        int i = 0;
        int n = nums.length - 1;
        while (i <= n) {
            int num = nums[i];
            int max = 0;
            int index = i;
            System.out.println("==i==" + i + "==num==" + num);
            for (int j = i + 1; j < i + num + 1; j++) {
                System.out.println("==max==" + max + "==j==" + nums[j]);
                if (max < nums[j]) {
                    max = nums[j];
                    index = j;
                }
            }
            System.out.println("==index==" + index);
            c++;
            i = index + i;

            if ((i + nums[i] + 1) > n) {
                break;
            }
        }
        System.out.println(c);
    }

    /**
     * 45 跳跃游戏II
     */
    private static void jump1(int[] nums) {
        int n = nums.length - 1;
        int steps = 0;
        while (n > 0) {
            for (int i = 0; i < n; i++) {
                if (i + nums[i] >= n) {
                    n = i;
                    steps ++;
                    break;
                }
            }
        }
        System.out.println(steps);
    }

    /**
     * 46 全排列
     * 可重复
     */
    private static void permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> output = new ArrayList<>();

        for (int num : nums) {
            output.add(num);
        }
        int n = nums.length;
        backtrack(n, output, res, 0);

        for (int i = 0; i < res.size(); i++) {
            List<Integer> integers = res.get(i);
            for (int j = 0; j < integers.size(); j++) {
                Integer integer = integers.get(j);
                System.out.print(integer + ",");
            }
            System.out.println();
        }
    }

    private static void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        if (n == first) {
            res.add(new ArrayList<>(output));
        }

        for (int i = first; i < n; i++) {
            System.out.println("=0=i==" + i + "==first==" + first);
            Collections.swap(output, first, i);

            System.out.println("=1=i==" + i + "==first==" + first);
            backtrack(n, output, res, first + 1);

            System.out.println("=2=i==" + i + "==first==" + first);
            Collections.swap(output, first, i);
        }
    }

    /**
     * 47 全排列II
     *
     * 不重复
     */
    private static void permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> perm = new ArrayList<>();
        boolean[] vis = new boolean[nums.length];

        Arrays.sort(nums);
        backtrack(nums, ans, 0, perm, vis);

        for (int i = 0; i < ans.size(); i++) {
            List<Integer> integers = ans.get(i);
            for (int j = 0; j < integers.size(); j++) {
                Integer integer = integers.get(j);
                System.out.print(integer + ",");
            }
            System.out.println();
        }
    }

    private static void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm, boolean[] vis) {
        if (idx == nums.length) {
            ans.add(new ArrayList<>(perm));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }
            perm.add(nums[i]);
            vis[i] = true;
            backtrack(nums, ans, idx + 1, perm, vis);
            vis[i] = false;
            perm.remove(idx);
        }
    }

    /**
     * 48 旋转图像
     * 顺时针旋转90度
     */
    private static void rotate(int[][] matrix) {
        int n = matrix[0].length;
        int[][] ans = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans[j][n - i - 1] = matrix[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(ans[i][j] + ",");
            }
            System.out.println();
        }
    }

    private static void rotate1(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + ",");
            }
            System.out.println();
        }
    }

}