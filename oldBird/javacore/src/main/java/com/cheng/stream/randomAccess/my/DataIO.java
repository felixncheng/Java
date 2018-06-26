package com.cheng.stream.randomAccess.my;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author cheng_mboy
 */
public class DataIO {


    public static String readFixString(int size,DataInput in) throws IOException {
        StringBuilder builder = new StringBuilder(size);
        for (int i=1 ;i<size;i++) {
            char c = in.readChar();
            if (c == 0) {
                in.skipBytes(2 * (size - i));
                break;
            }
            builder.append(c);
        }
        return builder.toString();
    }

    public static void writeFixString(String s, int size, DataOutput out)
            throws IOException {
        for (int i = 0; i < size; i++) {
            char ch = 0;
            if (i < s.length()) ch=s.charAt(i);
            out.writeChar(ch);
        }
    }
}
