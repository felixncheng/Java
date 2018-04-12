package com.chengmboy.jedis;

import redis.clients.jedis.Jedis;

/**
 * @author cheng_mboy
 */
public class JedisUtil {

    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis("192.168.0.2");
        String key = "m_klines_s_87_t_0";
    }
}
