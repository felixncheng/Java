package com.chengmboy;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chengmboy.util.common.StreamUtils;
import com.chengmboy.util.common.ThreadUtils;


/**
 * @author cheng_mboy
 */
public class Program {

    private static boolean ready;

    private static AtomicInteger num = new AtomicInteger(100000);

    public static void main(String[] args) throws InterruptedException, IOException {

        int a = 255;
        byte b = (byte) a;
        System.out.println(b);

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
