package com.zgf.javalib.algorithm;

public class ArrayAlgorithm {

    private ArrayAlgorithm() {
    }

    // 在已知数组中某个位置插入数据
    public static void arrayAddData(int[] array, int data, int index) {
        if (array == null) {
            return;
        }
        int length = array.length;
        if (index > length || index < 0) {
            return;
        }

        // 扩容
        int[] newArray = new int[length + 1];

        // 复制方式
//        System.arraycopy(array, 0, newArray, 0, index);
//        System.arraycopy(array, index, newArray, index + 1, newArray.length - index - 1);
//        newArray[index] = data;

        // 将老数组复制到新数组中
        System.arraycopy(array, 0, newArray, 0, length);
        // 将index后面的数据往后挪一位
        for (int i = newArray.length - 1; i > index; i--) {
            newArray[i] = newArray[i - 1];
        }
        newArray[index] = data;

        // for循环方式
//        for (int i = 0; i < length; i++) {
//            if (i == index) {
//                newArray[i] = data;
//                newArray[i + 1] = array[i];
//            } else if (i < index) {
//                newArray[i] = array[i];
//            } else {
//                newArray[i + 1] = array[i];
//            }
//        }

        // 打印结果
        int length1 = newArray.length;
        for (int i = 0; i < length1; i++) {
            System.out.println("========" + newArray[i]);
        }
    }
}
