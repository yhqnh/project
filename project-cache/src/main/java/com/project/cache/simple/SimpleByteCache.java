package com.project.cache.simple;

import com.project.cache.ByteCache;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.project.cache.proxy.Cache;
import com.project.cache.support.CachePara;

@Component
@Slf4j
public class SimpleByteCache extends ByteCache implements Cache {

	public void putValue2Cache(CachePara cachePara, Object result) throws Throwable {
		Byte putResult = (Byte) result;
		String key = (String) cachePara.getObjectKey();
		if (!StringUtils.isEmpty(putResult)) {
			if (cachePara.isSerializable()) {
				template.set(defaultSerializer.serialize(key), defaultSerializer.serialize(putResult), cachePara.expire);
			} else {
				template.set(key, Byte.toString(putResult), cachePara.expire);
			}
		}
	}
}
