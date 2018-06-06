package com.chengmboy.network.tcp.http;

import java.io.*;
import java.net.Socket;

/**
 * @author cheng_mboy
 */
public class HttpClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        try (PrintWriter pw = new PrintWriter(socket.getOutputStream());
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            pw.println("GET /person HTTP/1.1");
            pw.println("Connection: keep-alive");
            pw.println("");
            pw.flush();
            socket.shutdownOutput();
            String info;
            while ((info = br.readLine()) != null) {
                System.out.println(info);
            }
        }
    }
}
