package com.chengmboy.algorithm.string;

/**
 * @author cheng_mboy
 */
public class Characters {

    /**
     * 替换字符串
     *
     * @param s       需要替换字符串
     * @param oldChar 将被替换掉的字符
     * @param newChar 替换的字符
     * @return 替换后的字符串
     */
    static String replace(String s, String oldChar, String newChar) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        return s.replace(oldChar, newChar);
    }

    public static void main(String[] args) {
        System.out.println(replace("We are happy", " ", "%20"));
    }
}
