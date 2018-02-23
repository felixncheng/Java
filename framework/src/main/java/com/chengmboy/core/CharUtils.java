package com.chengmboy.core;

import java.util.Random;

/**
 * @author cheng_mboy
 */
public class CharUtils {

    public static char[] alphabets = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    /**
     * 随机返回一个英文字符
     */
    public static char next() {
        Random random = new Random();
        int i = random.nextInt(52);
        return alphabets[i];
    }

    /**
     * 随机返回一个大写字符
     */
    public static char nextUpperCase() {
        Random random = new Random();
        int i = random.nextInt(26);
        return alphabets[i];
    }

    /**
     * 随机返回一个小写字符
     */
    public static char nextLowerCase() {
        Random random = new Random();
        int i = 26 + random.nextInt(26);
        return alphabets[i];
    }
}
