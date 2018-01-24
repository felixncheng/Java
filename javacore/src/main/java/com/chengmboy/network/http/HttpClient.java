package com.chengmboy.network.http;

import java.io.*;
import java.net.Socket;

/**
 * @author cheng_mboy
 */
public class HttpClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8888);
        try (PrintWriter pw = new PrintWriter(socket.getOutputStream());
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            pw.println("GET /?abc=2 HTTP/1.1");
            pw.println("Connection: keep-alive");
            pw.println("");
            pw.flush();
            String info;
            while ((info = br.readLine()) != null) {
                System.out.println(info);
            }
        }
    }
}
