package com.chengmboy.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author cheng_mboy
 */
public class SocketServer {


    public static void main(String[] args) throws IOException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(100, 100,
                0, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>(), new ChatServerThreadFactory());
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(3000));

        while (true) {
            Socket accept = serverSocket.accept();
            try {
                InputStream inputStream = accept.getInputStream();
                OutputStream outputStream = accept.getOutputStream();

                poolExecutor.submit(() -> {
                    Scanner scanner = new Scanner(inputStream);
                    while (scanner.hasNextLine()) {
                        System.out.println(scanner.nextLine());
                    }
                });

                poolExecutor.submit(() -> {
                    try {
                        Scanner scanner = new Scanner(System.in);
                        while (scanner.hasNext()) {
                            int i = scanner.nextInt();
                            outputStream.write(i);
                            outputStream.flush();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ChatServerThreadFactory implements ThreadFactory {

        private final AtomicInteger threadNumber = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "chat_server_pool_" + threadNumber.getAndIncrement());
        }
    }

}

