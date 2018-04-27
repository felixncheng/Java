package com.chengmboy.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cheng_mboy
 */
public class SocketClient {

    public static void main(String[] args) throws IOException, InterruptedException {

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(100, 100,
                0, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>(), new ChatClientThreadFactory());

        Socket socket = new Socket("localhost", 3000);

        poolExecutor.submit(() -> {
            try {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    String say = scanner.nextLine();
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write(say.getBytes());
                    outputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        poolExecutor.submit(() -> {
            try {
                InputStream inputStream = socket.getInputStream();
                int read;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while ((read = inputStream.read()) != -1) {
                    if (read == 13) {
                        System.out.println(socket.getRemoteSocketAddress() + "说: " + new String(byteArrayOutputStream.toByteArray()));
                        byteArrayOutputStream = new ByteArrayOutputStream();
                    } else {
                        byteArrayOutputStream.write(read);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    private static class ChatClientThreadFactory implements ThreadFactory {

        private final AtomicInteger threadNumber = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "chat_client_pool_" + threadNumber.getAndIncrement());
        }
    }
}
