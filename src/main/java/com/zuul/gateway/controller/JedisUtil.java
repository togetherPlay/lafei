package com.zuul.gateway.controller;

import redis.clients.jedis.Jedis;

/**
 * RedisUtil
 * 
 * @author Administrator
 *
 */
public class JedisUtil {

	private volatile static JedisUtil jedisUtil = null;

	public JedisUtil() {
	}
	
	/**
	 * ˫������ȡJedisUtil����ʵ��
	 * @return
	 */
	public static JedisUtil getInstance(){
		if(jedisUtil == null){
			synchronized(JedisUtil.class){
				if(jedisUtil == null){
					jedisUtil = new JedisUtil();
				}
			}
		}
		return jedisUtil;
	}

	public Jedis getJedis(String ip, int port){
		Jedis jedis = new Jedis(ip,port);
		return jedis;
	}
}
