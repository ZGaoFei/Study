package com.zgf.javalib.offer;

import java.util.Stack;

/**
 * JZ9 用两个栈实现队列
 * <p>
 * 栈是先进后出
 * 队列是先进先出
 */
class CustomerQueue<T> {
    Stack<T> stack01 = new Stack<>();
    Stack<T> stack02 = new Stack<>();

    public T pop() {
        if (stack02.size() <= 0) {
            while (!stack01.isEmpty()) {
                stack02.push(stack01.pop());
            }
        }
        return stack02.pop();
    }

    public void push(T t) {
        stack01.push(t);
    }

}

/**
 * pop() 返回栈顶的值，并删除
 * peek() 返回栈顶的值
 */
class MinStack {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public int top() {
        return stack.peek();
    }

    public void push(int n) {
        stack.push(n);

        if (stack2.isEmpty() || stack2.peek() > n) {
            stack2.push(n);
        } else {
            stack2.push(stack2.peek());
        }
    }

    public void pop() {
        stack.pop();
        stack2.pop();
    }

    public int min() {
        return stack2.peek();
    }
}

class StackTest {
    public static void main(String[] args) {
        int[] push = new int[]{1, 2, 3, 4, 5};
        int[] pop = new int[]{1, 4, 5, 3, 2};
        isPopOrder(push, pop);

        String string = "people a am i";
        reverseSentence(string);
        reverseSentence1(string);
        reverseSentence2(string);

        int[] nums = new int[]{2,3,4,2,6,2,5,1};
        maxInWindows(nums, 3);
        System.out.println();
        maxInWindows1(nums, 3);
    }

    /**
     * JZ31 栈的压入、弹出序列
     * <p>
     * 判定第一个入栈顺序能否得到第二个出栈顺序
     */
    private static void isPopOrder(int[] push, int[] pop) {
        Stack<Integer> temp = new Stack<>();

        int n = push.length;
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (j < n && (temp.isEmpty() || temp.peek() != pop[i])) {
                temp.push(push[j]);
                j++;
            }

            if (temp.peek() == pop[i]) {
                temp.pop();
            } else {
                System.out.println("false");
                return;
            }
        }
        System.out.println("true");
    }

    /**
     * JZ73 翻转单词序列
     * <p>
     * people a am i
     * i am a people
     */
    private static void reverseSentence(String string) {
        Stack<String> stack = new Stack<>();

        String[] s = string.split(" ");
        for (String value : s) {
            stack.push(value);
        }
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pop()).append(" ");
        }

        System.out.println(builder.toString());
    }

    private static void reverseSentence1(String string) {
        String[] s = string.split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = s.length - 1; i >= 0; i--) {
            builder.append(s[i]).append(" ");
        }

        System.out.println(builder.toString());
    }

    private static void reverseSentence2(String string) {
        char[] chars = string.toCharArray();
        reverseRange(chars, 0, chars.length - 1);

        int start = 0;
        int end = 0;
        while (start < chars.length) {
            if (chars[start] == ' ') {
                start++;
                end++;
            } else if (end == chars.length || chars[end] == ' ') {
                reverseRange(chars, start, end - 1);
                end++;
                start = end;
            } else {
                end++;
            }
        }

        System.out.println(new String(chars));
    }

    private static void reverseRange(char[] chars, int start, int end) {
        while (start < end) {
            reverse(chars, start, end);
            start++;
            end--;
        }
    }

    private static void reverse(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    /**
     * JZ59 滑动窗口的最大值
     *
     * 暴力法
     * 先分出对应的数组，然后计算各个数组的最大值
     */
    private static void maxInWindows(int[] nums, int size) {
        int n = nums.length;
        int[][] temp = new int[n - size + 1][size];
        int start = 0;
        int i = 0;
        while (start + size <= n) {
            int[] item = push(nums, start, start + size, size);
            temp[i] = item;
            start++;
            i++;
        }
        print(temp);

        for (int j = 0; j < temp.length; j++) {
            int max = max(temp[j]);
            System.out.print(max + " ");
        }
    }

    /**
     * JZ59 滑动窗口的最大值
     * 1 2 3 4 5 6
     * 3
     *
     * 判断数组区间内的最大值
     */
    private static void maxInWindows1(int[] nums, int size) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i + size - 1 < n) {
                int max = nums[i];
                for (int j = i + 1; j < i + size; j++) {
                    if (max < nums[j]) {
                        max = nums[j];
                    }
                }
                System.out.print(max + " ");
            }
        }
    }

    private static int[] push(int[] src, int start, int end, int size) {
        int[] res = new int[size];
        int i = 0;
        while (start < end) {
            res[i] = src[start];
            i++;
            start++;
        }
        return res;
    }

    private static int max(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
        }
        return max;
    }

    private static void print(int[][] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                System.out.print(nums[i][j] + " ");
            }
            System.out.println();
        }
    }

}
