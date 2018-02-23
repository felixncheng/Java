package com.chengmboy.network;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author cheng_mboy
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8888);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(97);
        socket.shutdownOutput();
    }
}
