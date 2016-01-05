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
public class SimpleByteCacher implements Cache {

	@Resource
	RedisClientTemplate template;

	@Autowired
	private RedisSerializer<Object> defaultSerializer;

	@Override
	public Object cacheAbled(CachePara cachePara) throws Throwable {
		String key = (String) cachePara.getObjectKey();
		String cache = template.get(key);
		Byte result = null;

		if (StringUtils.isEmpty(cache)) {
			result = (Byte) cachePara.getPjp().proceed();
			if (!StringUtils.isEmpty(result)) {
				Integer expire = cachePara.getExpire();
				template.set(key, Byte.toString(result), expire);
			}
		} else {
			result = Byte.parseByte(cache);
		}
		return result;
	}

	@Override
	public Object cacheEvict(CachePara cachePara) throws Throwable {
		String key = (String) cachePara.getObjectKey();
		Byte result = (Byte) cachePara.getPjp().proceed();
		if (!StringUtils.isEmpty(key)) {
			template.del(key);
		}
		return result;
	}

	@Override
	public Object cachePut(CachePara cachePara) throws Throwable {
		String key = (String) cachePara.getObjectKey();
		Byte result = (Byte) cachePara.getPjp().proceed();
		if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(result)) {
			Integer expire = cachePara.getExpire();
			template.set(key, Byte.toString(result), expire);
		}
		return result;
	}
}
