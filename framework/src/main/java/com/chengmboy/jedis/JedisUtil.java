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
        Jedis jedis = new Jedis("localhost");
        ThreadPoolExecutor executor = ThreadUtils.getThreadPoolExecutor(3, 3);
        executor.execute(()-> jedis.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println(channel+": "+message);
            }
        },"chat"));
        Jedis jedis12 = new Jedis("localhost");
        jedis12.publish("chat", "hello");
    }
}
