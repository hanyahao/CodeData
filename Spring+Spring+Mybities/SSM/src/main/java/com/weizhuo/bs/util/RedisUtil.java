/*
 * Copyright 2011-2015 10jqka.com.cn All right reserved. This software is the confidential and proprietary information
 * of 10jqka.com.cn (Confidential Information"). You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into with 10jqka.com.cn.
 */
package com.weizhuo.bs.util;

import com.weizhuo.bs.core.common.AppConfig;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Set;

//import com.awifi.sms.biz.HDSmsSend;

/**
 * 类RedisUtil.java的实现描述：
 * 
 * @author kfpanda 2014-7-14 上午10:55:45
 */

public class RedisUtil {
	private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);
	private static final String configFilePath = "/properties/application.properties";
	private static JedisPool pool = null;
	static {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(100);
		poolConfig.setMaxIdle(5);
		poolConfig.setMaxWaitMillis(1000 * 100);
		// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
		poolConfig.setTestOnBorrow(true);
		pool = new JedisPool(poolConfig, AppConfig.get("cache.redis.ip"),
				Integer.parseInt(StringUtils.isNumeric(AppConfig.get("cache.redis.port")) ? AppConfig.get("cache.redis.port") : "6379"));

	}

	public static Jedis getResource() {
		return pool.getResource();
	}

	public static void returnResource(Jedis resource) {
		pool.returnResourceObject(resource);
	}

	/*
	 * 释放redis对象。
	 */
	@Deprecated
	public static void returnBrokenResource(Jedis resource) {
		pool.returnBrokenResource(resource);
	}

	public static void hdelForFields(String key, List<String> fields) {
		if (key!=null && !"".equals(key) && fields != null && fields.size() > 0) {
			Jedis jedis = RedisUtil.getResource();
			try {
				for (String field : fields) {
					long result = jedis.hdel(key, field);
					logger.debug("Redis.hdelForFields set: result({}).", result);
				}
			} catch (Exception e) {
				// 释放redis对象
				//pool.returnResourceObject(jedis);
				e.printStackTrace();
			} finally {
				RedisUtil.returnResource(jedis);
			}
		}
	}

	public static void hdel(String key, String field) {
		if (key!=null && !"".equals(key) && field!=null && !"".equals(field)) {
			Jedis jedis = RedisUtil.getResource();
			try {
				long result = jedis.hdel(key, field);
				logger.debug("Redis.hdel set: result({}).", result);
			} catch (Exception e) {
				// 释放redis对象
				//pool.returnBrokenResource(jedis);
				e.printStackTrace();
			} finally {
				RedisUtil.returnResource(jedis);
			}
		}
	}

	public static List<String> hvals(String key) {
		List<String> result = null;
		if (key!=null && !"".equals(key)) {
			Jedis jedis = RedisUtil.getResource();
			try {
				result = jedis.hvals(key);
				logger.debug("Redis.hvals : result({}).", result);
			} catch (Exception e) {
				// 释放redis对象
				//pool.returnBrokenResource(jedis);
				e.printStackTrace();
			} finally {
				RedisUtil.returnResource(jedis);
			}
		}

		return result;
	}

	public static Set<String> hkeys(String key) {
		Set<String> result = null;
		if (key!=null && !"".equals(key)) {
			Jedis jedis = RedisUtil.getResource();
			try {
				result = jedis.hkeys(key);
				logger.debug("Redis.hkeys : result({}).", result);
			} catch (Exception e) {
				// 释放redis对象
				//pool.returnBrokenResource(jedis);
				e.printStackTrace();
			} finally {
				RedisUtil.returnResource(jedis);
			}
		}
		return result;
	}

	public static void expire(String key, int seconds) {
		if (key!=null && !"".equals(key)) {
			Jedis jedis = RedisUtil.getResource();
			try {
				Long result = jedis.expire(key, seconds);
				logger.debug("Redis.expire result for key: key({}), result({}).", key, result);
			} catch (Exception e) {
				// 释放redis对象
				//pool.returnBrokenResource(jedis);
				e.printStackTrace();
			} finally {
				RedisUtil.returnResource(jedis);
			}
		}
	}

}
