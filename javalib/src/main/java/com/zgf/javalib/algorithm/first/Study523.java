package com.zgf.javalib.algorithm.first;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

class Study523 {
    public static void main(String[] args) {
//        int[] nums = new int[] {1, 2, 3, 5, 4};
//        nextPermutation(nums);
//        Utils.printArray(nums);
//        System.out.println();
//
//        int[] nums1 = new int[] {1, 5, 8, 4, 7, 6, 5, 3, 1};
//        nextPermutation(nums1);
//        Utils.printArray(nums1);

//        String s = "())(()())(()";
//        longestValidParentheses(s);

//        int[] nums = new int[]{3, 4, 5, 6, 7, 0, 1, 2};
//        int search = search(nums, 8);
//        System.out.println(search);
//
//        int[] nums1 = new int[]{3, 4, 5, 6, 7, 9};
//        int search1 = binarySearch(nums1, 3);
//        System.out.println(search1);
//
//        int[] nums2 = new int[]{3, 3, 4, 5, 5, 5, 5, 6, 7};
//        int[] search2 = searchRange(nums2, 3);
//        Utils.printArray(search2);
//        System.out.println();
//
//        int search11 = binarySearch1(nums1, 3);
//        System.out.println("===search11===" + search11);

//        String a = "123111";
//        String count = count(a);
//        System.out.println(count);

        countAndSay(6);
    }

    /**
     * 31 下一个排列
     * <p>
     * 当年面试字节的一道算法题
     */
    private static void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        }

        int i = nums.length - 2;
        // 第一遍找到第一个逆序的位置
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // 找到了这个位置
        if (i >= 0) {
            int j = nums.length - 1;
            // 找到第一个大于先前找到的逆序的值
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            // 交换位置
            swap(nums, i, j);
        }

        // 将后面的逆序全部反转过来
        reverse(nums, i + 1);
    }

    private static void swap(int[] nums, int i, int j) {
        int a = nums[i];
        nums[i] = nums[j];
        nums[j] = a;
    }

    private static void reverse(int[] nums, int start) {
        int left = start;
        int right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    /**
     * 32 最长有效括号
     */
    private static void longestValidParentheses(String s) {
        int length = s.length();
        int maxans = 0;
        Deque<Integer> list = new LinkedList<>();
        list.push(-1);
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                list.push(i);
            } else {
                list.pop();
                if (list.isEmpty()) {
                    list.push(i);
                } else {
                    maxans = Math.max(maxans, i - list.peek());
                }
            }
        }

        System.out.println(maxans);
    }

    /**
     * 33 搜索旋转排序数组
     * <p>
     * 数组是有序数组经过某一个位置旋转后的数组，
     * 所以数组可以分为两部分，这两部分是分别有序的
     * <p>
     * 采用二分查找法
     * 其实就是一个在一个两段有序的数组中查找是否包含target
     */
    private static int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) { // target在0-mid之间
                    r = mid - 1;
                } else { // target在mid-r之间
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[r]) { // target在mid-r之间
                    l = mid + 1;
                } else { // target在0-mid之间
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    // 二分查找法
    private static int binarySearch(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        System.out.println("====llll======" + l);
        return -1;
    }

    private static int binarySearch(int[] nums, int target, boolean lower) {
        int l = 0;
        int r = nums.length - 1;
        int ans = nums.length;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                r = mid - 1;
                ans = mid;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    /**
     * @param nums
     * @param target
     * @return
     */
    private static int binarySearch1(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int ans = nums.length;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] >= target) {
                r = mid - 1;
                ans = mid;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 34 在排序数组中查找元素的第一个和最后一个位置
     * <p>
     * [1, 2, 3, 4, 5, 5, 6] target = 5
     * [4, 5]
     */
    private static int[] searchRange(int[] nums, int target) {
        int leftIndex = binarySearch(nums, target, true);
        int rightIndex = binarySearch(nums, target, false) - 1;
        System.out.println(leftIndex + "====" + rightIndex);
        if (leftIndex <= rightIndex
                && rightIndex <= nums.length - 1
                && nums[leftIndex] == target
                && nums[rightIndex] == target) {
            return new int[]{leftIndex, rightIndex};
        } else {
            return new int[]{-1, -1};
        }
    }

    /**
     * 38 外观数列
     *
     * 先找出一个字符串的所有数字出现的次数
     * 再根据n来决定遍历多少次
     */
    private static void countAndSay(int n) {
        String ans = "1";
        for (int i = 1; i < n; i++) {
            ans = count(ans);
            System.out.println(ans);
        }
        System.out.println(ans);
    }

    // 遍历一个字符串拼出所有字符出现的次数
    private static String count(String s) {
        int i = 1;
        StringBuilder ans = new StringBuilder();
        for (int j = 1; j < s.length(); j++) {
            char left = s.charAt(j - 1);
            char right = s.charAt(j);
            if (left == right) {
                i++;
            } else {
                ans.append(i).append(left);
                i = 1;
            }
        }
        ans.append(i).append(s.charAt(s.length() - 1));
        return ans.toString();
    }
}
