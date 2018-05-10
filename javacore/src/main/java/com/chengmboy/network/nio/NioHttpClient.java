package com.chengmboy.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;

/**
 * @author cheng_mboy
 */
public class NioHttpClient {

    public static void main(String[] args) throws IOException {
        InetSocketAddress remote = new InetSocketAddress("localhost", 8080);
        SocketChannel client = SocketChannel.open();
        client.bind(new InetSocketAddress(5000));
        client.connect(remote);
        ByteBuffer buffer = ByteBuffer.allocate(4098);
        String request = "GET /basic/dispatch HTTP/1.1\r\n"
                + "Host: localhost:8080\r\n\r\n";
        client.write(ByteBuffer.wrap(request.getBytes()));
        WritableByteChannel output = Channels.newChannel(System.out);
        while (client.read(buffer) != -1) {
            buffer.flip();
            output.write(buffer);
            buffer.clear();
        }
    }
}
