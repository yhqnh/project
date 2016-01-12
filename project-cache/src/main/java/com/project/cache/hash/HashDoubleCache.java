package com.project.cache.hash;

import javax.annotation.Resource;

import com.project.cache.DoubleCache;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.project.cache.factory.Cache;
import com.project.cache.support.CachePara;
import com.project.cache.support.RedisClientTemplate;

@Component
@Slf4j
public class HashDoubleCache extends DoubleCache implements Cache {

	public void putValue2Cache(CachePara cachePara, Object result) throws Throwable {
		Double putResult = (Double) result;
		String key = (String) cachePara.getObjectKey();
		String filed = (String) cachePara.getObjectField();
		int expire = cachePara.getExpire();
		if (!StringUtils.isEmpty(putResult)) {
			if (cachePara.isSerializable()) {
				template.hset(defaultSerializer.serialize(key), defaultSerializer.serialize(filed), defaultSerializer.serialize(putResult), expire);
			} else {
				template.hset(key, filed, Double.toString(putResult), expire);
			}
		}
	}
}