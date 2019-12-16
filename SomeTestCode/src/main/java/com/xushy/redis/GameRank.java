package com.xushy.redis;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

/**
 * @author xushy
 * @description redis 实现游戏排行 使用reids命令 1.zadd 2.zrevrangeWithScores 分数从高到低 3.zrangeWithScores 分数从低到高
 * @date 2019年11月27日
 */
public class GameRank {
    private static String[] surNames = new String[] {"赵", "钱", "孙", "李", "周"};
    private static String[] finalNames = new String[] {"一", "三", "五", "七", "九"};

    public static void main(String[] args) {
        Random random = new Random();
        String gameName = "阴阳师";
        Map<String, Double> userScore = new HashMap<String, Double>();
        userScore.put(surNames[random.nextInt(5)] + finalNames[random.nextInt(5)], Math.random());
        userScore.put(surNames[random.nextInt(5)] + finalNames[random.nextInt(5)], Math.random());
        userScore.put(surNames[random.nextInt(5)] + finalNames[random.nextInt(5)], Math.random());
        userScore.put(surNames[random.nextInt(5)] + finalNames[random.nextInt(5)], Math.random());
        userScore.put(surNames[random.nextInt(5)] + finalNames[random.nextInt(5)], Math.random());
        userScore.put(surNames[random.nextInt(5)] + finalNames[random.nextInt(5)], Math.random());
        // addRank(gameName, userScore);
        System.out.println(gameName + " top five list");
        Set<Tuple> tupleList = getRank(gameName, 5, 1);
        for (Tuple tuple : tupleList) {
            System.out.println("玩家姓名:" + tuple.getElement() + "玩家分数:" + tuple.getScore());
        }
    }

    public static void addRank(String gameName, Map<String, Double> userScore) {
        JedisPool jedisPool = JedisPoolFactory.Instance.INSTANCE.getJedisPool();
        Jedis jedis = jedisPool.getResource();
        try {
            // for (Entry<String, Double> entry : userScore.entrySet()) {
            // jedis.zadd(gameName, entry.getValue(), entry.getKey());
            // }
            jedis.zadd(gameName, userScore);
        } finally {
            // jedis.disconnect() 没有返还给线程池,需要使用close
            jedis.close();
        }
    }

    /**
     * @param gameName
     * @param end
     *            前多少名
     * @param order
     *            0 升序 1 降序
     * @return
     */
    public static Set<Tuple> getRank(String gameName, int end, int order) {
        JedisPool jedisPool = JedisPoolFactory.Instance.INSTANCE.getJedisPool();
        Jedis jedis = jedisPool.getResource();
        try {
            if (order == 0) {
                return jedis.zrangeWithScores(gameName, 0, end);// 升序排列
            } else {
                return jedis.zrevrangeWithScores(gameName, 0, end);// 升序排列
            }
        } finally {
            jedis.close();
        }
    }
}
