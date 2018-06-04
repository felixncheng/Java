package com.chengmboy.util.common;

/**
 * @author cheng_mboy
 */
public class StringUtils {

    public static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

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

    /**
     * 将字节数组转换成16进制字符串
     */
    public static String toHexString(byte[] bytes) {
        StringBuilder r = new StringBuilder();
        for (byte b : bytes) {
            r.append(HEX_DIGITS[(b & 0xf0) >> 4])
                    .append(HEX_DIGITS[b & 0xf]);
        }
        return r.toString();
    }

    /**
     * 将字符串第一个字符变成大写
     */
    public static String upperCaseFirst(String str) {
        char c = str.charAt(0);
        if (!Character.isUpperCase(c)) {
            str = Character.toUpperCase(c) + str.substring(1, str.length());
        }
        return str;
    }

    /**
     * 将字符串第一个字符变成小写
     */
    public static String lowerCaseFirst(String str) {
        char c = str.charAt(0);
        if (!Character.isLowerCase(c)) {
            str = Character.toLowerCase(c) + str.substring(1, str.length());
        }
        return str;
    }
}
