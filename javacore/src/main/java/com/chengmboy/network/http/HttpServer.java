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
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter pw = new PrintWriter(socket.getOutputStream())) {
                String requestHeader;
                int contentLength = 0;
                while ((requestHeader = reader.readLine()) != null && !requestHeader.isEmpty()) {
                    System.out.println(requestHeader);
                    if (requestHeader.startsWith("content-length:")) {
                        int i = requestHeader.indexOf(":");
                        String s = requestHeader.substring(i + 2);
                        contentLength = Integer.valueOf(s);
                    }
                }
                StringBuilder sb = new StringBuilder();
                if (contentLength > 0) {
                    for (int i = 0; i < contentLength; i++) {
                        char read = (char) reader.read();
                        sb.append(read);
                    }
                    System.out.println(sb);
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
}
