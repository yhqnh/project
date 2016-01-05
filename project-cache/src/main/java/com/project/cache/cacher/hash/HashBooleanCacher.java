package com.project.cache.cacher.hash;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.project.cache.factory.Cache;
import com.project.cache.support.CachePara;
import com.project.cache.support.RedisClientTemplate;

@Component
@Slf4j
public class HashBooleanCacher implements Cache {

	@Resource
	RedisClientTemplate template;

	@Override
	public Object cacheAbled(CachePara cachePara) throws Throwable {
		String key = (String) cachePara.getObjectKey();
		String filed = (String) cachePara.getObjectField();
		String cache = template.hget(key,filed);
		Boolean result = null;

		if (StringUtils.isEmpty(cache)) {
			result = (Boolean) cachePara.getPjp().proceed();
			if (!StringUtils.isEmpty(result)) {
				Integer expire = cachePara.getExpire();
				template.hset(key, filed, Boolean.toString(result), expire);
			}
		} else {
			result = Boolean.parseBoolean(cache);
		}
		return result;
	}

	@Override
	public Object cacheEvict(CachePara cachePara) throws Throwable {
		String key = (String) cachePara.getObjectKey();
		Boolean result = (Boolean) cachePara.getPjp().proceed();
		if (!StringUtils.isEmpty(key)) {
			template.hdel(key);
		}
		return result;
	}

	@Override
	public Object cachePut(CachePara cachePara) throws Throwable {
		String key = (String) cachePara.getObjectKey();
		String filed = (String) cachePara.getObjectField();
		Boolean result = (Boolean) cachePara.getPjp().proceed();
		if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(result)) {
			Integer expire = cachePara.getExpire();
			template.hset(key, filed, Boolean.toString(result), expire);
		}
		return result;
	}
}
