package com.xushy.redis;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * Apache Commons-pool2
 * 参考 https://help.aliyun.com/document_detail/98726.html#section-m2c-5kr-zfb
 * */
public class JedisPoolFactory {
	private static Logger logger = LoggerFactory.getLogger(JedisPoolFactory.class);
	@Getter
	private JedisPool jedisPool;
	private JedisPoolConfig jedisPoolConfig;
	
	private JedisPoolFactory() {
		jedisPoolConfig = new JedisPoolConfig();
		//默认为8
		jedisPoolConfig.setMaxTotal(10);
		//默认为8
		jedisPoolConfig.setMaxIdle(10);
		//默认为0
		jedisPoolConfig.setMinIdle(0);
		//默认-1L 表示永不超时 
		jedisPoolConfig.setMaxWaitMillis(10000L);
		jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 1000/*,密码*/);
		preheat();
    }

    private void preheat() {
    	List<Jedis> minIdleJedisList = new ArrayList<Jedis>(jedisPoolConfig.getMinIdle());
    	for (int i = 0; i < jedisPoolConfig.getMinIdle(); i++) {
    	    Jedis jedis = null;
    	    try {
    	        jedis = jedisPool.getResource();
    	        minIdleJedisList.add(jedis);
    	        jedis.ping();
    	    } catch (Exception e) {
    	        logger.error(e.getMessage(), e);
    	    } finally {
    	    }
    	}
    	for (int i = 0; i < jedisPoolConfig.getMinIdle(); i++) {
    	    Jedis jedis = null;
    	    try {
    	        jedis = minIdleJedisList.get(i);
    	        jedis.close();
    	    } catch (Exception e) {
    	        logger.error(e.getMessage(), e);
    	    } finally {

    	    }
    	}
	}

	public enum Instance{
        INSTANCE;
        
        private JedisPoolFactory factory;
        
        Instance() {
        	factory = new JedisPoolFactory();
        }
        
        public JedisPoolFactory getFactory(){
            return factory;
        }
    }
}
