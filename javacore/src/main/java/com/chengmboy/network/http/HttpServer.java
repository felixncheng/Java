package com.chengmboy.network.http;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author cheng_mboy
 */
public class HttpServer {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8888);
        while (true) {
            Socket socket = ss.accept();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try (InputStream inputStream = socket.getInputStream();
                 PrintWriter pw = new PrintWriter(socket.getOutputStream())) {
                int read;
                int lastIndex = 0;
                int contentLength = 0;
                while ((read = inputStream.read()) != -1) {
                    outputStream.write(read);
                    byte[] array = outputStream.toByteArray();
                    if (isLine(array)) {
                        int i = array.length - lastIndex;
                        byte[] line = new byte[i];
                        System.arraycopy(array, lastIndex, line, 0, i);
                        String requestHeader = new String(line);
                        lastIndex = array.length;
                        if (requestHeader.startsWith("content-length:")) {
                            int k = requestHeader.indexOf(":");
                            String s = requestHeader.substring(k + 2, requestHeader.length() - 2);
                            contentLength = Integer.valueOf(s);
                        }
                        System.out.print(requestHeader);
                    }
                    if (isOver(array)) {
                        break;
                    }
                }
                if (contentLength > 0) {
                    for (int i = 0; i < contentLength; i++) {
                        outputStream.write(inputStream.read());
                    }
                }
                byte[] bytes = outputStream.toByteArray();
                for (int i = lastIndex; i < bytes.length; i++) {
                    System.out.print(bytes[i] + " ");
                }
                pw.println("HTTP/1.1 200 OK");
                pw.println("Content-type:application/json;charset=UTF-8");
                pw.println();
                pw.println("{\n" +
                        "\t\"data\": \"访问成功\"\n" +
                        "}");
            }
            socket.close();
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

    private static boolean isOver(byte[] bytes) {
        int length = bytes.length;
        if (length < 4) {
            return false;
        }
        if (bytes[length - 1] == 10 && bytes[length - 2] == 13 && bytes[length - 3] == 10 && bytes[length - 4] == 13) {
            return true;
        }
        return false;
    }
}
