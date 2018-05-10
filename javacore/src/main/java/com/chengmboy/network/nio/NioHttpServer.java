package com.chengmboy.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author cheng_mboy
 */
public class NioHttpServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(3000));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        String header = "HTTP/1.1 200 OK\r\n\r\n" +
                "Hello World!";
        byte[] headerData = header.getBytes();
        ByteBuffer responseBuffer = ByteBuffer.wrap(headerData);
        while (true) {
            selector.select();
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();
                try {
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        System.out.println("接受到连接" + client);
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);
                    }
                    if (key.isReadable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(4098);
                        client.read(buffer);
                        key.attach(responseBuffer.duplicate());
                        key.interestOps(SelectionKey.OP_WRITE);
                    }
                    if (key.isWritable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        if (buffer.hasRemaining()) {
                            client.write(buffer);
                        } else {
                            client.close();
                        }
                    }
                } catch (IOException e) {
                    key.cancel();
                    key.channel().close();
                }
            }
        }
    }
}
