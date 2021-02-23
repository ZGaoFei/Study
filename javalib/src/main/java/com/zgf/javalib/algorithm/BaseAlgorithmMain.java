package com.zgf.javalib.algorithm;


public class BaseAlgorithmMain {

    public static void main(String[] args) {
        int[] array = {0, 2, 4, 7, 9, 3, 6, 8, 1, 5};

        int index0 = BaseAlgorithm.search0(array, 3);
        System.out.println("index0: " + index0);

        int index1 = BaseAlgorithm.search1(array, 3);
        System.out.println("index1: " + index1);

        int[] sortArray = {0, 1, 2, 3, 4, 6, 7, 8, 9};
        int i0 = BaseAlgorithm.binarySearch0(sortArray, 5);
        System.out.println("i0: " + i0);

        int i1 = BaseAlgorithm.binarySearch1(sortArray, 5);
        System.out.println("i1: " + -i1);

        int fibonacci = BaseAlgorithm.fibonacci(4);
        System.out.println("fibonacci: " + fibonacci);

        int[] fibonacciArray = BaseAlgorithm.fibonacciArray(9);
        for (int i = 0; i < fibonacciArray.length; i++) {
            System.out.println("fibonacciArray i: " + i + " value: " + fibonacciArray[i]);
        }
    }
}

class BaseAlgorithm {

    // search
    public static int search0(int[] array, int data) {
        if (array == null) {
            return -1;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == data) {
                return i;
            }
        }
        return -1;
    }

    // search
    public static int search1(int[] array, int data) {
        if (array == null) {
            return -1;
        }
        int index = array.length - 1;
        if (data == array[index]) {
            return index;
        }
        array[index] = data;
        int i = 0;
        while (array[i++] != data) ;
        return i == index + 1 ? -1 : i - 1;
    }

    // binary search
    public static int binarySearch0(int[] array, int data) {
        if (array == null) {
            return -1;
        }
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            // int middle = (low + high) >> 1;
            // 防止溢出
            int middle = low + ((high - low) >> 1);
            if (array[middle] == data) {
                return middle;
            } else if (data < array[middle]) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }

    /**
     * android ArrayMap内实现的二分查找法
     * <p>
     * >> 有符号位移 正数右移高位补1， 负数右移高位补0
     * >>> 无符号位移 右移高位统统补0
     * <p>
     * 查找不到返回当前要查找的值应该在数组中的位置的取反
     */
    public static int binarySearch1(int[] array, int data) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midValue = array[mid];
            if (midValue < data) {
                low = mid + 1;
            } else if (midValue > data) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return ~low;
    }

    // 生成斐波那契第n位的值
    public static int fibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // 生成长度为n的斐波那契数组
    public static int[] fibonacciArray(int n) {
        int[] array = new int[n];
        array[0] = 0;
        array[1] = 1;
        for (int i = 2; i < n; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array;
    }

}

