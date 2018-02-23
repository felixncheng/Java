package com.chengmboy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * @author cheng_mboy
 */
public class Program {

    private static boolean ready;

    public static void main(String[] args) throws IOException {
       int read=255;
        byte value = (byte) read;
        char a= 0x0060;
        System.out.println(value);
        System.out.println(a);
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
