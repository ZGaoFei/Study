package com.zgf.javalib.offer;

/**
 * 归并排序算法
 */
public class MergeSortTest {

    public static void main(String[] args) {
        int[] nums = new int[]{5, 3, 2, 6, 8, 4, 9, 0, 1, 7};
        sort(nums);
    }

    private static void sort(int[] nums) {
        if (nums == null) {
            return;
        }
        int[] temp = new int[nums.length];
        mergeSort(0, nums.length - 1, nums, temp);

        for (int k = 0; k < temp.length; k++) {
            System.out.print(temp[k] + " ");
        }
        System.out.println();
        for (int k = 0; k < nums.length; k++) {
            System.out.print(nums[k] + " ");
        }
    }

    // 拆分过程
    private static void mergeSort(int left, int right, int[] res, int[] temp) {
        if (left < right) { // 拆分边界
            int mid = (left + right) / 2;
            // 左拆分
            mergeSort(left, mid, res, temp);
            // 右拆分
            mergeSort(mid + 1, right, res, temp);

            // 合并
            merge(left, right, res, temp, mid);
        }
    }

    // 合并过程
    private static void merge(int left, int right, int[] res, int[] temp, int mid) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        // 取最小的放入
        while (i <= mid && j <= right) {
            if (res[i] <= res[j]) {
                temp[t] = res[i];
                i++;
            } else {
                temp[t] = res[j];
                j++;
            }
            t++;
        }
        // 如果左边数组还有值，接着放入
        while (i <= mid) {
            temp[t] = res[i];
            i++;
            t++;
        }
        // 如果右边数组还有值，接着放入
        while (j <= right) {
            temp[t] = res[j];
            j++;
            t++;
        }

        // printArray(res);
        printArray(temp);


        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            res[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
