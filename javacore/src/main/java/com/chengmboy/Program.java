package com.chengmboy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * @author cheng_mboy
 */
public class Program {

    private static boolean ready;

    public static void main(String[] args) throws IOException {
        byte[] b1 = {102, 108, 95, 13, 10, 85, 65, 42, 13, 10};
        byte[] dest = new byte[32];
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int lastIndex = 0;
        for (byte b : b1) {
            if (b != -1) {
                outputStream.write(b);
                byte[] array = outputStream.toByteArray();
                if (isLine(array)) {
                    int i = array.length - lastIndex;
                    byte[] line = new byte[i];
                    System.arraycopy(array, lastIndex, line, 0, i);
                    System.out.println(new String(line));
                    lastIndex = array.length;
                }
            }
        }
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
