package com.xushy.redis;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisController {
    // countdownlatch
    private static CountDownLatch latch = new CountDownLatch(10000);

    public static Jedis initJedis(String host, Integer port) {
        if (StringUtils.isNotBlank(host)) {
            return new Jedis(host, port);
        }
        return new Jedis();
    }

    public static void main(String[] args) throws InterruptedException {
        GenericObjectPoolConfig<?> config = new GenericObjectPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(100);
        final JedisPool pool = new JedisPool(config, "127.0.0.1", 6379);
        // final Jedis jedis = initJedis("127.0.0.1", 6379);
        // jedis.connect();
        final AtomicInteger atomic = new AtomicInteger(30000);
        for (int i = 0; i < 10002; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    latch.countDown();
                    Jedis jedis = pool.getResource();
                    System.out.println(System.currentTimeMillis()
                        + jedis.set("test" + atomic.get(), "" + atomic.getAndIncrement()));
                    jedis.close();
                }
            };
            thread.start();
        }
        latch.await();
        // long reply = jedis.append("test", "my first redis test");
        // String replyStr = jedis.psetex("complete", 10000,
        // "my first redis test");

        System.out.println("still alive");
    }
}
