package com.zgf.javalib.algorithm.first;

import java.util.HashMap;
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

        String[] strings = {"flower", "flow", "flight"};
        String s = longestCommonPrefix(strings);
        System.out.println(s);
        String[] strings1 = {"dog", "racecar", "car"};
        String s1 = longestCommonPrefix(strings1);
        System.out.println(s1);
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
}
