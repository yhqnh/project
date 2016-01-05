package com.project.cache.cacher.simple;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.project.cache.factory.Cache;
import com.project.cache.support.CachePara;
import com.project.cache.support.RedisClientTemplate;

@Component
@Slf4j
public class SimpleIntegerCacher implements Cache {

	@Resource
	RedisClientTemplate template;

	@Override
	public Object cacheAbled(CachePara cachePara) throws Throwable {
		String key = (String) cachePara.getObjectKey();
		String cache = template.get(key);
		Integer result = null;

		if (StringUtils.isEmpty(cache)) {
			result = (Integer) cachePara.getPjp().proceed();
			if (!StringUtils.isEmpty(result)) {
				Integer expire = cachePara.getExpire();
				template.set(key, Integer.toString(result), expire);
			}
		} else {
			result = Integer.parseInt(cache);
		}
		return result;
	}

	@Override
	public Object cacheEvict(CachePara cachePara) throws Throwable {
		String key = (String) cachePara.getObjectKey();
		Integer result = (Integer) cachePara.getPjp().proceed();
		if (!StringUtils.isEmpty(key)) {
			template.del(key);
		}
		return result;
	}

	@Override
	public Object cachePut(CachePara cachePara) throws Throwable {
		String key = (String) cachePara.getObjectKey();
		Integer result = (Integer) cachePara.getPjp().proceed();
		if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(result)) {
			Integer expire = cachePara.getExpire();
			template.set(key, Integer.toString(result), expire);
		}
		return result;
	}
}
