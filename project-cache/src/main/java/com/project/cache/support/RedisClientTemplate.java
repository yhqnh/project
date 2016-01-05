package com.project.cache.support;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Component
@Slf4j
public class RedisClientTemplate {

	@Value("#{redisConfigProperties['jedis.expire']}")
	private int defaultExpire;

	@Autowired
	private ShardedJedisPool shardedJedisPool;

	@Autowired
	private RedisSerializer<Object> defaultSerializer;

	private void close(ShardedJedis shardedJedis) {
		if (StringUtils.isEmpty(shardedJedis)) {
			return;
		}
		shardedJedis.close();
	}

	private Long persist(ShardedJedis shardedJedis, String key) {
		log.debug("redis persist key:" + key);
		return shardedJedis.persist(key);
	}

	private Long persist(ShardedJedis shardedJedis, byte[] key) {
		log.debug("redis persist key:" + defaultSerializer.deserialize(key));
		return shardedJedis.persist(key);
	}

	private Long expireOrPersist(ShardedJedis shardedJedis, String key, int expire) {

		if (StringUtils.isEmpty(expire)) {
			return expire(shardedJedis, key, expire);
		}

		if (expire < 0) {
			return expire(shardedJedis, key, expire);
		} else if (expire > 0) {
			return expire(shardedJedis, key, expire);
		} else {
			return persist(shardedJedis, key);
		}
	}

	private Long expire(ShardedJedis shardedJedis, String key, int expire) {
		if (StringUtils.isEmpty(expire)) {
			expire = defaultExpire;
		}

		if (expire < 0) {
			expire = defaultExpire;
		}

		log.debug("redis set data key:" + key);
		log.debug("redis set data expire:" + expire);
		return shardedJedis.expire(key, expire);
	}

	private Long expireOrPersist(ShardedJedis shardedJedis, byte[] key, int expire) {

		if (StringUtils.isEmpty(expire)) {
			return expire(shardedJedis, key, expire);
		}

		if (expire < 0) {
			return expire(shardedJedis, key, expire);
		} else if (expire > 0) {
			return expire(shardedJedis, key, expire);
		} else {
			return persist(shardedJedis, key);
		}
	}

	private Long expire(ShardedJedis shardedJedis, byte[] key, int expire) {
		if (StringUtils.isEmpty(expire)) {
			expire = defaultExpire;
		}

		if (expire < 0) {
			expire = defaultExpire;
		}
		log.debug("redis set data key:" + key);
		log.debug("redis set data expire:" + expire);
		return shardedJedis.expire(key, expire);
	}

	public String set(String key, String value, int expire) {
		String result = null;
		ShardedJedis shardedJedis = shardedJedisPool.getResource();
		try {
			result = shardedJedis.set(key, value);
			this.expireOrPersist(shardedJedis, key, expire);
		} finally {
			close(shardedJedis);
		}
		return result;
	}

	public String set(byte[] key, byte[] value, int expire) {
		String result = null;
		ShardedJedis shardedJedis = shardedJedisPool.getResource();
		try {
			result = shardedJedis.set(key, value);
			this.expireOrPersist(shardedJedis, key, expire);
		} finally {
			close(shardedJedis);
		}
		return result;
	}

	public Long hset(String key, String field, String value, int expire) {
		Long result = null;
		ShardedJedis shardedJedis = shardedJedisPool.getResource();
		try {
			result = shardedJedis.hset(key, field, value);
			this.expireOrPersist(shardedJedis, key, expire);
		} finally {
			close(shardedJedis);
		}
		return result;
	}

	public Long hset(byte[] key, byte[] field, byte[] value, int expire) {
		Long result = null;
		ShardedJedis shardedJedis = shardedJedisPool.getResource();
		try {
			result = shardedJedis.hset(key, field, value);
			this.expireOrPersist(shardedJedis, key, expire);
		} finally {
			close(shardedJedis);
		}
		return result;
	}

	public String get(String key) {
		String result = null;
		ShardedJedis shardedJedis = shardedJedisPool.getResource();
		try {
			result = shardedJedis.get(key);
		} finally {
			close(shardedJedis);
		}
		log.debug("redis get data key:" + key);
		log.debug("redis get data value:" + result);
		return result;
	}

	public byte[] get(byte[] key) {
		byte[] result = null;
		ShardedJedis shardedJedis = shardedJedisPool.getResource();
		try {
			result = shardedJedis.get(key);
		} finally {
			close(shardedJedis);
		}
		log.debug("redis get data key:" + defaultSerializer.deserialize(key));
		log.debug("redis get data value:" + new Gson().toJson(defaultSerializer.deserialize(result)));
		return result;
	}

	public String hget(String key, String field) {
		String result = null;
		ShardedJedis shardedJedis = shardedJedisPool.getResource();
		try {
			result = shardedJedis.hget(key, field);
		} finally {
			close(shardedJedis);
		}
		log.debug("redis get data key:" + key);
		log.debug("redis get data field:" + field);
		log.debug("redis get data value:" + result);
		return result;
	}

	public byte[] hget(byte[] key, byte[] field) {
		byte[] result = null;
		ShardedJedis shardedJedis = shardedJedisPool.getResource();
		try {
			result = shardedJedis.hget(key, field);
		} finally {
			close(shardedJedis);
		}
		log.debug("redis get data key:" + defaultSerializer.deserialize(key));
		log.debug("redis get data field:" + defaultSerializer.deserialize(field));
		log.debug("redis get data value:" + new Gson().toJson(defaultSerializer.deserialize(result)));
		return result;
	}

	public Long del(String key) {
		Long result = null;
		ShardedJedis shardedJedis = shardedJedisPool.getResource();
		try {
			result = shardedJedis.del(key);
		} finally {
			close(shardedJedis);
		}
		return result;
	}

	public Long del(byte[] key) {
		Long result = null;
		ShardedJedis shardedJedis = shardedJedisPool.getResource();
		try {
			result = shardedJedis.del(key);
		} finally {
			close(shardedJedis);
		}
		return result;
	}
	
	public Long hdel(String key) {
		Long result = null;
		ShardedJedis shardedJedis = shardedJedisPool.getResource();
		try {
			result = shardedJedis.hdel(key);
		} finally {
			close(shardedJedis);
		}
		return result;
	}

	public Long hdel(byte[] key) {
		Long result = null;
		ShardedJedis shardedJedis = shardedJedisPool.getResource();
		try {
			result = shardedJedis.hdel(key);
		} finally {

			close(shardedJedis);
		}
		return result;
	}
}
