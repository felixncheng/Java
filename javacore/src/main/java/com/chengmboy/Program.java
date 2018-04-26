package com.chengmboy;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chengmboy.util.common.ThreadUtils;


/**
 * @author cheng_mboy
 */
public class Program {

    private static boolean ready;

    private static AtomicInteger num = new AtomicInteger(100000);

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

        ScheduledFuture<?> schedule = service.schedule(() -> {
            try {
                System.out.println("开始线程状态" + Thread.interrupted());
                long time = System.currentTimeMillis();
                while ((System.currentTimeMillis() - time < 3000)) {

                }
                System.out.println("中止后线程状态" + Thread.interrupted());
            } finally {
                System.out.println("finally");
            }
        }, 3, TimeUnit.SECONDS);

        Thread.sleep(3100);
        schedule.cancel(false);
        System.out.println("线程已被中断");


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
