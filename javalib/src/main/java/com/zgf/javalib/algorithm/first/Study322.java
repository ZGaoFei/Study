package com.zgf.javalib.algorithm.first;

class Study322 {

    public static void main(String[] args) {
//        System.out.println(isPalindrome(1000));
//        System.out.println(isPalindrome(1001));
//        System.out.println(isPalindrome(10001));
//        System.out.println(isPalindrome(10000));
//        System.out.println(isPalindrome(1));
//        System.out.println(isPalindrome(0));
//        System.out.println(isPalindrome(-1000));
//        System.out.println("============");
//        System.out.println(isPalindrome0(1000));
//        System.out.println(isPalindrome0(1001));
//        System.out.println(isPalindrome0(10001));
//        System.out.println(isPalindrome0(10000));
//        System.out.println(isPalindrome0(1));
//        System.out.println(isPalindrome0(0));
//        System.out.println(isPalindrome0(-1000));

        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        maxArea(height);
        maxArea0(height);
    }

    /**
     * 9 回文数
     * <p>
     * 普通解法，数字转字符
     */
    private static boolean isPalindrome(int num) {
        if (num < 0) {
            return false;
        }
        String numStr = String.valueOf(num);
        int length = numStr.length();
        for (int i = 0; i < length / 2; i++) {
            System.out.println(numStr.charAt(i) + "====" + numStr.charAt(length - 1 - i));
            if (numStr.charAt(i) != numStr.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;

        // 一行解决
        // return new StringBuilder(num).reverse().toString().equals(num + "");
    }

    /**
     * 9 回文数
     * <p>
     * 将后面的数字反转过来对比
     */
    private static boolean isPalindrome0(int num) {
        // num 小于0，或者末尾是0 但num不是0 的时候不是回文数
        if (num < 0 || (num % 10 == 0 && num != 0)) {
            return false;
        }

        int reverseNum = 0;
        while (reverseNum < num) {
            reverseNum = reverseNum * 10 + num % 10;
            num = num / 10;
        }
        return reverseNum == num || reverseNum / 10 == num;
    }

    /**
     * 11 盛最多水的容器
     *
     * 计算每个组合取最大的
     */
    private static void maxArea(int[] height) {
        int area = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                // 先取最小高度
                int max = Math.min(height[i], height[j]);
                System.out.println("max: " + max);
                // 取当前面积与新面积的最大值
                area = Math.max(area, max * (j - i));
                System.out.println("area: " + area);
            }
        }
        System.out.println(area);
    }

    /**
     * 11 盛最多水的容器
     *
     * 双指针法
     */
    private static void maxArea0(int[] height) {
        int start = 0;
        int end = height.length - 1;
        int area = 0;
        while (start < end) {
            int min = Math.min(height[start], height[end]);
            area = Math.max(area, min * (end - start));

            if (height[start] <= height[end]) {
                start ++;
            } else {
                end --;
            }
        }
        System.out.println(area);
    }
}
