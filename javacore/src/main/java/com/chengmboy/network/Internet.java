package com.chengmboy.network;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.chengmboy.util.StreamUtils;

/**
 * @author cheng_mboy
 */
public class Internet {

    public static void main(String[] args) throws IOException {
        StringBuilder str = new StringBuilder();
        for (int i=0;i<5000;i++) {
             str.append(i);
        }
        String data = str.toString();
        byte[] bytes = data.getBytes();
        System.out.println("压缩前数据: "+ data);
        System.out.println("压缩前数据大小: "+bytes.length);
        ByteArrayOutputStream compressedBytes = new ByteArrayOutputStream();
        try (GZIPOutputStream outputStream = new GZIPOutputStream(compressedBytes)){
            outputStream.write(bytes);
        }

        System.out.println("压缩后数据大小: "+ compressedBytes.toByteArray().length);

        GZIPInputStream inputStream = new GZIPInputStream(new ByteArrayInputStream(compressedBytes.toByteArray()));

       ByteArrayOutputStream deCompressedBytes = new ByteArrayOutputStream();
        StreamUtils.copy(inputStream, deCompressedBytes);
        System.out.println("解压后数据大小："+deCompressedBytes.toByteArray().length);
        System.out.println("解压后数据："+ new String(deCompressedBytes.toByteArray()));

        byte[] bytes1 = new byte[10000 * 2];
        GZIPInputStream inputStream1 = new GZIPInputStream(new ByteArrayInputStream(compressedBytes.toByteArray()));
        inputStream1.read(bytes1);

        for (byte b : deCompressedBytes.toByteArray()) {
            System.out.print(b+" ");
        }

        System.out.println();
        for (byte b : bytes1) {
            System.out.print(b+" ");
        }

        System.out.println();
        System.out.println("解压后数据："+new String(bytes1));

    }

    private static void showAddress() throws UnknownHostException {
        byte[] address = {127, 0, 0, 1};
        InetAddress inetAddress = InetAddress.getByAddress(address);
        System.out.println(inetAddress);
    }
}
