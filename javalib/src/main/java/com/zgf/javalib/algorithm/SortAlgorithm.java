package com.zgf.javalib.algorithm;

// 排序算法
class SortAlgorithm {

    public static void main(String[] args) {
        int[] array = {3, 5, 8, 2, 6, 1, 0, 9, 4, 7};
        sortInsert(array);
        print(array);

        int[] array1 = {3, 5, 8, 2, 6, 1, 0, 9, 4, 7};
        sortInsert2(array1);
        print(array1);

        int[] array2 = {3, 5, 8, 2, 6, 1, 0, 9, 4, 7};
        sortSimple(array2);
        print(array2);

        int[] array3 = {3, 5, 8, 2, 6, 1, 0, 9, 4, 7};
        sortBubble(array3);
        print(array3);

        int[] array4 = {3, 5, 8, 2, 6, 1, 0, 9, 4, 7};
        sortHeap(array4);
        print(array4);
    }

    private static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println();
    }

    /**
     * 插入排序01
     *
     * 1、从第一个元素开始，该元素可以认为已经被排序
     * 2、取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 3、如果该元素（已排序）大于新元素，将该元素移到下一位置
     * 4、重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 5、将新元素插入到该位置后
     * 6、重复步骤2~5
     */
    private static void sortInsert(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }


    /**
     * 插入排序02
     *
     * 通过将较大的元素都向右移动而不总是交换两个元素
     * 1、取出当前位置的数据num
     * 2、j > 0 && num < array[j - 1] 符合这个条件的会j--
     * 3、将大的往后移动覆盖当前位置直到条件不符合，此时j就是当前的位置
     * 4、然后将num赋值给j所在位置的数据
     *
     * 平均时间复杂度、最好情况、最坏情况O(n*n)
     * 空间复杂度O(1)
     */
    private static void sortInsert2(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int num = array[i];
            int j;
            for (j = i; j > 0 && num < array[j - 1]; j--) {
                array[j] = array[j - 1];
            }
            array[j] = num;
        }
    }

    /**
     * 简单排序算法
     *
     * 1、先找到数组中最小的元素放到首位
     * 2、再找到次小的元素放在第二位
     * 3、依次类推
     *
     * 平均时间复杂度、最好情况、最坏情况O(n*n)
     * 空间复杂度O(1)
     */
    private static void sortSimple(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = a[i];
                a[i] = a[min];
                a[min] = temp;
            }
        }
    }

    /**
     * 冒泡排序算法
     *
     * 1、比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 2、对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
     * 3、针对所有的元素重复以上的步骤，除了最后一个。
     * 4、持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     */
    private static void sortBubble(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 堆排序
     */
    private static void sortHeap(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            maxHeap(a, i);

            // 堆顶元素(第一个元素)与Kn交换
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
        }
    }

    /**
     * 生成最大堆
     *  i = 第一个非叶子节点。
     *  从第一个非叶子节点开始即可。无需从最后一个叶子节点开始。
     *  叶子节点可以看作已符合堆要求的节点，根节点就是它自己且自己以下值为最大。
     */
    private static void maxHeap(int[] a, int n) {
        int child;
        for (int i = (n - 1) / 2; i >= 0; i--) {
            // 左子节点位置
            child = 2 * i + 1;
            // 右子节点存在且大于左子节点，child变成右子节点
            if (child != n && a[child] < a[child + 1]) {
                child ++;
            }
            // 交互父节点与左右子节点中的最大值
            if (a[i] < a[child]) {
                int temp = a[i];
                a[i] = a[child];
                a[child] = temp;
            }
        }
    }
}
