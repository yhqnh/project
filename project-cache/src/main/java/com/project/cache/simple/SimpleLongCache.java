package com.project.cache.simple;

import com.project.cache.LongCache;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.project.cache.proxy.Cache;
import com.project.cache.support.CachePara;

@Component
@Slf4j
public class SimpleLongCache extends LongCache implements Cache {

	public void putValue2Cache(CachePara cachePara, Object result) throws Throwable {
		Long putResult = (Long) result;
		String key = (String) cachePara.getObjectKey();
		if (!StringUtils.isEmpty(putResult)) {
			if (cachePara.isSerializable()) {
				template.set(defaultSerializer.serialize(key), defaultSerializer.serialize(putResult), cachePara.expire);
			} else {
				template.set(key, Long.toString(putResult), cachePara.expire);
			}
		}
	}
}
