package com.chengmboy.util.common;

import java.io.*;

/**
 * @author cheng_mboy
 */
public class StreamUtils {

    private final static int BUFFER_SIZE = 8192;

    /**
     * 复制InputStream内容到OutputStream
     *
     * @param in  复制的数据来源输入流
     * @param out 复制到的输出流
     * @return 总共复制的字节数
     * @throws IOException 发生IO错误
     */
    public static int copy(InputStream in, OutputStream out) throws IOException {
        int byteCount = 0;
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
            byteCount += bytesRead;
        }
        out.flush();
        return byteCount;
    }
}
