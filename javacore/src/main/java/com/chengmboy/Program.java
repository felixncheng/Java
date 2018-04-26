package com.chengmboy;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
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

    public static void main(String[] args) {
        String sql = "insert into dock_market_data(market_from,symbol,type,open,high,low,close,volume,volume_money,start_id,end_id)" +
                " values (?,?,?,?,?," +
                "?,?,?,?,?," +
                "?)";
        sql = sql.toUpperCase();
        String valueSql = sql.substring(sql.indexOf("VALUES") + 6);

        StringBuilder builder = new StringBuilder(sql);
        for (int i = 5; i > 0; i--) {
            builder.append(",")
                    .append(valueSql);
        }
        System.out.println(builder.toString());
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
