package com.zuul.gateway.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zuul.gateway.controller.JedisUtil;

import redis.clients.jedis.Jedis;

public class RedisLock {

	private static Logger logger = LoggerFactory.getLogger(RedisLock.class);
	Jedis jedis = JedisUtil.getInstance().getJedis("localhost", 6379);
	/**
	 * lock path
	 */
	private String lockKey;

	/**
	 * 锁超时时间，防止线程在入锁以后，无限的执行等待
	 */
	private int expireMesc = 6 * 1000;
	
	public String getLock(){
		return lockKey;
	}
	
	private String get(final String key){
		Object obj = null;
		
		obj = jedis.get(key);
		return obj!=null?obj.toString():null;
	}

}
