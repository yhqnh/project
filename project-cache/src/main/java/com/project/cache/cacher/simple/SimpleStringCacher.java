package com.project.cache.cacher.simple;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.project.cache.factory.Cache;
import com.project.cache.support.CachePara;
import com.project.cache.support.RedisClientTemplate;

@Component
@Slf4j
public class SimpleStringCacher implements Cache {

	@Autowired
	RedisClientTemplate template;
	
	@Override
	public Object cacheAbled(CachePara cachePara) throws Throwable {
		String key = (String) cachePara.getObjectKey();
		String result = template.get(key);
		if (StringUtils.isEmpty(result)) {
			result = (String) cachePara.getPjp().proceed();
			if (!StringUtils.isEmpty(result)) {
				Integer expire = cachePara.getExpire();
				template.set(key, result, expire);
			}
		}
		return result;
	}

	@Override
	public Object cacheEvict(CachePara cachePara) throws Throwable {
		String key = (String) cachePara.getObjectKey();
		String result = (String) cachePara.getPjp().proceed();
		if (!StringUtils.isEmpty(key)) {
			template.del(key);
		}
		return result;
	}

	@Override
	public Object cachePut(CachePara cachePara) throws Throwable {
		String key = (String) cachePara.getObjectKey();
		String result = (String) cachePara.getPjp().proceed();
		if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(result)) {
			Integer expire = cachePara.getExpire();
			template.set(key, result, expire);
		}
		return result;
	}
}
