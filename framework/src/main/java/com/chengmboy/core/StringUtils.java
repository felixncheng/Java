package com.chengmboy.core;

/**
 * @author cheng_mboy
 */
public class StringUtils {

    /**
     * 将字符串倒序重组
     */
    public static String reverse(String src) {
        char[] chars = src.toCharArray();
        char[] newChars = new char[src.length()];
        int oldIndex = chars.length - 1;
        for (int i = 0; i < newChars.length; i++) {
            newChars[i] = chars[oldIndex];
            oldIndex--;
        }
        return new String(newChars);
    }
}
