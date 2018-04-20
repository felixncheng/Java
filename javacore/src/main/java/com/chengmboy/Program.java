package com.chengmboy;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chengmboy.util.common.ThreadUtils;


/**
 * @author cheng_mboy
 */
public class Program {

    private static boolean ready;

    private static AtomicInteger num = new AtomicInteger(100000);

    public static void main(String[] args) throws IOException, InterruptedException {

        Object o = new Object();
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(100, 1000000,
                0L, TimeUnit.MICROSECONDS, new SynchronousQueue<>(),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            executorService.submit(() ->
            {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                num.getAndDecrement();
                System.out.println(executorService);
            });
        }
        out:
        while (true) {
            while (num.get() == 0) {
                long end = System.currentTimeMillis();
                System.out.println("执行时间 "+ (end - start));
                break out;
            }
        }
        executorService.shutdown();
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

    static class User {

        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
