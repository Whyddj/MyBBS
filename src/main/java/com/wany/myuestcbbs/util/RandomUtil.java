package com.wany.myuestcbbs.util;

public class RandomUtil {
    public static String getRandomNumber(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((int) (Math.random() * 10));
        }
        return sb.toString();
    }
}
