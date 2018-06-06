package com.chengmboy.jedis;

import java.util.*;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chengmboy.datasource.DataBase;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author cheng_mboy
 */
public class JedisUtil {

    private static int[] MULITIPLE = {1, 5, 15, 24 * 60, 7 * 24 * 60, 31 * 24 * 60, 365 * 24 * 60, 0, 0, 30, 60};
    private static Jedis jedis = new Jedis("192.168.0.2");


    public static void main(String[] args) throws InterruptedException {

        int[] ids = {87, 101, 102, 103, 104, 105, 106, 107,108,113,116,117,123};
        //int[] types = {0, 1, 2, 9, 10, 3, 4, 5, 6};

        //int[] ids = {117};
        int[] types = {0, 1, 2, 9, 10, 3, 4, 5, 6};

        deleteAll(ids, types);
/*
       for (int i = 3; i > 0; i--) {
            delete();
        }*/

       /* long delete = delete("*sum*");
        System.out.println(delete);*/
    }

    private static long delete(String pattern) {
        Set<String> keys = jedis.keys(pattern);
        return jedis.del(keys.toArray(new String[keys.size()]));
    }

    private static void delete() {
        int[] ids = {87, 101, 102, 103, 104, 105, 106, 107, 108, 113, 116, 117};
        int[] types = {0, 1, 2, 9, 10, 3, 4, 5, 6};
        for (int id : ids) {
            for (int type : types) {
                jedis.rpop("m_klines_s_" + id + "_t_" + type);
            }
        }

    }

    /**
     * 限速器
     */
    public static boolean speedGovernor(String key, int max, int seconds) {
        String current = jedis.get(key);
        if (current != null && Integer.valueOf(current) > max) {
            return false;
        }
        if (current == null) {
            Transaction ts = jedis.multi();
            ts.incr(key);
            ts.expire(key, seconds);
            ts.exec();
        } else {
            jedis.incr(key);
        }
        return true;
    }

    private static void getKeys() {
        int[] huobiIds = {79, 69, 68, 78};
        int[] types = {0};

        for (int id : huobiIds) {
            for (int type : types) {
                System.out.println("m_klines_s_" + id + "_t_" + type);
            }
        }
    }


    private static void deleteAll(int[] ids, int[] types) {
        for (int id : ids) {
            for (int type : types) {
                jedis.del("m_klines_s_" + id + "_t_" + type);
            }
        }
        String url = "jdbc:mysql://192.168.0.3:3306/coin_sub?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf-8";
        String username = "digi";
        String password = "digi123456";
        String driver = "com.mysql.jdbc.Driver";
        DataBase dataBase = new DataBase(url, username, password, driver);
        int i = dataBase.excuteUpdate("delete from dock_market_data;");
        System.out.println(i);
    }

    private static void validateKline(int[] ids, int[] types) {
        for (int id : ids) {
            for (int type : types) {
                List<String> list = jedis.lrange("m_klines_s_" + id + "_t_" + type, 0, 2879);
                for (int i = 0; i < list.size() - 1; i++) {
                    JSONObject object = JSON.parseObject(list.get(i));
                    Long time = object.getLong("createdDate");
                    JSONObject next = JSON.parseObject(list.get(i + 1));
                    Date nextDate = next.getDate("createdDate");

                    long l = time + MULITIPLE[type] * 60 * 1000L;
                    Date nextLineDate = new Date(l);

                    if (type == 5) {
                        Calendar c = Calendar.getInstance();
                        c.setTime(nextDate);
                        int nextm = c.get(Calendar.MONTH);
                        c.setTime(nextLineDate);
                        int nextLinem = c.get(Calendar.MONTH);
                        if (nextm != nextLinem) {
                            System.out.println(new Date(time));
                            System.out.println(nextDate);
                            System.out.println(nextLineDate);
                            System.out.println(object);
                            System.out.println(next);
                        }
                    } else if (type == 6) {
                        Calendar c = Calendar.getInstance();
                        c.setTime(nextDate);
                        int nexty = c.get(Calendar.YEAR);
                        c.setTime(nextLineDate);
                        int nextLiney = c.get(Calendar.YEAR);
                        if (nexty != nextLiney) {
                            System.out.println(new Date(time));
                            System.out.println(nextDate);
                            System.out.println(nextLineDate);
                            System.out.println(object);
                            System.out.println(next);
                        }
                    } else {
                        if (!Objects.equals(nextDate, nextLineDate)) {
                            System.out.println(new Date(time));
                            System.out.println(nextDate);
                            System.out.println(nextLineDate);
                            System.out.println(object);
                            System.out.println(next);
                        }
                    }
                }
            }
        }
    }
}
