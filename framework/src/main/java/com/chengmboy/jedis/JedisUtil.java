package com.chengmboy.jedis;

import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

import com.chengmboy.util.ThreadUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * @author cheng_mboy
 */
public class JedisUtil {

    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis("192.168.0.2");
        String key = "m_klines_s_87_t_0";
    }
}
