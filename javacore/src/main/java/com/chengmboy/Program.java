package com.chengmboy;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * @author cheng_mboy
 */
public class Program {

    private static boolean ready;

    public static void main(String[] args) throws IOException {
        String symbolName = "zecusdt";
        String req = "market." + symbolName + ".kline.1min";
       String a = "{ \"req\" :\""+req+"\", \"id\": \"req"+symbolName+"\"}";
        System.out.println(a);
    }

    static class User{
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
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
}
