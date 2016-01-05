package com.project.cache.cacher.simple;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.project.cache.factory.Cache;
import com.project.cache.support.CachePara;
import com.project.cache.support.RedisClientTemplate;

@Component
@Slf4j
public class SimpleObjectCacher implements Cache {

	@Resource
	RedisClientTemplate template;

	@Autowired
	private RedisSerializer<Object> defaultSerializer;
	
	@Override
	public Object cacheAbled(CachePara cachePara) throws Throwable {
		byte[] key = defaultSerializer.serialize(cachePara.getObjectKey());
		byte[] cache = template.get(key);
		Object result = null;
		if (StringUtils.isEmpty(cache)) {
			result = cachePara.getPjp().proceed();
			if (!StringUtils.isEmpty(result)) {
				Integer expire = cachePara.getExpire();
				template.set(key, defaultSerializer.serialize(result), expire);
			}
		} else {
			result = defaultSerializer.deserialize(cache);
		}
		return result;
	}

	@Override
	public Object cacheEvict(CachePara cachePara) throws Throwable {
		byte[] key = defaultSerializer.serialize(cachePara.getObjectKey());
		Object result = cachePara.getPjp().proceed();
		if (!StringUtils.isEmpty(key)) {
			template.del(key);
		}
		return result;
	}

	@Override
	public Object cachePut(CachePara cachePara) throws Throwable {
		byte[] key = defaultSerializer.serialize(cachePara.getObjectKey());
		Object result = cachePara.getPjp().proceed();
		if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(result)) {
			Integer expire = cachePara.getExpire();
			template.set(key, defaultSerializer.serialize(result), expire);
		}
		return result;
	}
}
