package com.chengmboy;

import java.io.IOException;


/**
 * @author cheng_mboy
 */
public class Program {

    private static boolean ready;

    public static void main(String[] args) throws IOException {
        String s = "abc";
        System.out.println(s.charAt(0)+" "+s.charAt(s.length()-1));
    }

    private static boolean isLine(byte[] bytes) {
        int length = bytes.length;
        if (length < 2) {
            return false;
        }
        if (bytes[length - 1] == 10 && bytes[length - 2] == 13) {
            return true;
        }
        return false;
    }
}
