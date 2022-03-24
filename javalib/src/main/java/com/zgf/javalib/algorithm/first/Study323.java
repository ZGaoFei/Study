package com.zgf.javalib.algorithm.first;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Study323 {
    public static void main(String[] args) {
//        intToRoman(1);
//        intToRoman(3);
//        intToRoman(4);
//        intToRoman(9);
//        intToRoman(58);
//        intToRoman(1994);
//
//        romanToInt("I");
//        romanToInt("III");
//        romanToInt("IV");
//        romanToInt("IX");
//        romanToInt("LVIII");
//        romanToInt("MCMXCIV");

//        String[] strings = {"flower", "flow", "flight"};
//        String s = longestCommonPrefix(strings);
//        System.out.println(s);
//        String[] strings1 = {"dog", "racecar", "car"};
//        String s1 = longestCommonPrefix(strings1);
//        System.out.println(s1);

        int[] nums = {1, 2, 0, -1, -1};
        // threeNumSum(nums);

        // threeNumClosest(nums, -3);

        Character[] characters = {'2', '5', '9'};
        // backTrack(characters);

        backTrack0(characters);
    }

    /**
     * 12 整数转罗马数字
     * <p>
     * I    1
     * V    5
     * X    10
     * L    50
     * C    100
     * D    500
     * M    1000
     * <p>
     * 特殊情况
     * 4   IV
     * 9   IX
     * 40  XL
     * 90  XC
     * 400 CD
     * 900 CM
     */
    private static void intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder builder = new StringBuilder();

        // 从最大的数来处理
        for (int i = 0; i < values.length; i++) {
            int value = values[i];
            String symbol = symbols[i];

            // 如3000，3个1000
            while (num >= value) {
                // 递减，直到为0
                num -= value;
                builder.append(symbol);
            }
            if (num == 0) {
                break;
            }
        }
        System.out.println(builder.toString());
    }

    /**
     * 13 罗马数字转整数
     * <p>
     * MCMXCIV
     * M CM XC IV
     * 1000 + 900 + 90 + 4
     */
    private static void romanToInt(String romanStr) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        for (int i = 0; i < romanStr.length(); ++i) {
            int value = map.get(romanStr.charAt(i));
            if (i < romanStr.length() - 1 && value < map.get(romanStr.charAt(i + 1))) {
                result -= value;
            } else {
                result += value;
            }
        }
        System.out.println(result);
    }

    /**
     * 14 最长公共前缀
     *
     * 根据第一个字符串的每个字符去匹配其他的字符串的每个字符
     */
    private static String longestCommonPrefix(String[] strings) {
        StringBuilder builder = new StringBuilder();
        String string = strings[0];
        // 从一个字符串的第一个字符开始
        for (int i = 0; i < string.length(); i++) {
            // 标志位，只有一圈比较完之后才能确定是否是公共前缀
            boolean isOne = false;
            for (int j = 1; j < strings.length; j++) {
                // 边界检查，如果有字符串的长度小于i，表示该字符串较短，直接退出循环
                if (strings[j].length() <= i) {
                    return builder.toString();
                }
                if (string.charAt(i) != strings[j].charAt(i)) {
                    isOne = false;
                    break;
                }
                isOne = true;
            }
            System.out.println(isOne);
            if (isOne) {
                builder.append(string.charAt(i));
            }
        }
        return builder.toString();
    }

    /**
     * 15 三数之和
     *
     * 数组中所有的三个数之和为0
     * 遍历的方式，没有去重
     */
    private static void threeNumSum(int[] nums) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    System.out.println(nums[i] + "==" + nums[j] + "==" + nums[k]);
                    int sum = nums[i] + nums[j] + nums[k];
                    System.out.println(sum);
                    if (sum == 0) {
                        builder.append("[")
                                .append(nums[i]).append(",")
                                .append(nums[j]).append(",")
                                .append(nums[k])
                                .append("]");
                        // 去重
                        break;
                    }
                }
            }
        }
        builder.append("]");
        System.out.println(builder.toString());
    }

    /**
     * 16 最接近的三数之和
     *
     * 三数之和最接近target的值
     * 遍历所有组合，取最接近目标值的和
     */
    private static void threeNumClosest(int[] nums, int target) {
        int result = Integer.MAX_VALUE;

        // 区分目标值大于还是小于0
        // 目标值与和的差值，
        // 大于0时，最大的差值为MAX_VALUE，
        // 小于0时，最大的差值为MIN_VALUE
        int a = Integer.MAX_VALUE; // 差值
        if (target < 0) {
            a = Integer.MIN_VALUE;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    int b = target - sum;
                    System.out.println(sum + "==b==" + b + "==a==" + a);
                    // 目标值大于0时，差值取小的
                    if (target > 0) {
                        if (a > b) {
                            a = b;
                            result = sum;
                        }
                    } else {
                        if (a < b) {
                            a = b;
                            result = sum;
                        }
                    }
                }
            }
        }
        System.out.println(result);
    }

    /**
     * 17 电话号码的字母组合
     *
     * 随意输入一组数字，对应输出电话上字母的所有组合
     * 0 <= digits.length <= 4
     * digits[i] = ['2', '9'] 字符数组的取值
     */
    private static void backTrack(Character[] digits) {
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "nmo");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        int length = digits.length;
        String[] strings = new String[length];
        for (int i = 0; i < length; i++) {
            String s = map.get(digits[i]);
            strings[i] = s;
        }

        Utils.printArray(strings);

        if (length == 0) {
            System.out.println("");
        } else if (length == 1) {
            for (int i = 0; i < strings[0].length(); i++) {
                System.out.println(strings[0].charAt(i));
            }
        } else if (length == 2) {
            for (int i = 0; i < strings[0].length(); i++) {
                for (int j = 0; j < strings[1].length(); j++) {
                    System.out.println(strings[0].charAt(i) + "" + strings[1].charAt(j));
                }
            }
        } // length == 3, length == 4
    }

    private static void backTrack0(Character[] digits) {
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "nmo");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        int length = digits.length;
        if (length == 0) {
            System.out.println("");
        } else if (length == 1) {
            for (int i = 0; i < map.get(digits[0]).length(); i++) {
                System.out.println(map.get(digits[0]).charAt(i));
            }
        } else {
            String s = map.get(digits[0]);
            List<String> list = new ArrayList<>();
            // 先装填第一个list
            for (int i = 0; i < s.length(); i++) {
                list.add(s.charAt(i) + "");
            }

            // 依次取出后面的String进行拼接，因此每次都是两个for循环
            for (int i = 1; i < length; i++) {
                String s1 = map.get(digits[i]);
                list = append(s1, list);
            }

            // 输出
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i) + "  ");
            }
        }

    }

    /**
     * 对两个集合进行拼接
     */
    private static List<String> append(String s, List<String> stringList) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            for (int j = 0; j < stringList.size(); j++) {
                String s1 = stringList.get(j);
                s1 += c;
                list.add(s1);
            }
        }
        return list;
    }
}
