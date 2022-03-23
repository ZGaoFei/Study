package com.zgf.javalib.algorithm.first;

import java.util.HashMap;
import java.util.Map;

public class Study323 {
    public static void main(String[] args) {
        intToRoman(1);
        intToRoman(3);
        intToRoman(4);
        intToRoman(9);
        intToRoman(58);
        intToRoman(1994);

        romanToInt("I");
        romanToInt("III");
        romanToInt("IV");
        romanToInt("IX");
        romanToInt("LVIII");
        romanToInt("MCMXCIV");
    }

    /**
     * 12 整数转罗马数字
     *
     * I    1
     * V    5
     * X    10
     * L    50
     * C    100
     * D    500
     * M    1000
     *
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
     *
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
}
