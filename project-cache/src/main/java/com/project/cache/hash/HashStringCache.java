package com.project.cache.hash;

import com.project.cache.StringCache;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.project.cache.proxy.Cache;
import com.project.cache.support.CachePara;

@Component
@Slf4j
public class HashStringCache extends StringCache implements Cache {

    public void putValue2Cache(CachePara cachePara, Object result) throws Throwable {
        String putResult = (String) result;
        String key = (String) cachePara.getObjectKey();
        String filed = (String) cachePara.getObjectField();
        int expire = cachePara.getExpire();
        if (!StringUtils.isEmpty(putResult)) {
            if (cachePara.isSerializable()) {
                template.hset(defaultSerializer.serialize(key), defaultSerializer.serialize(filed), defaultSerializer.serialize(putResult), expire);
            } else {
                template.hset(key, filed, putResult, expire);
            }
        }
    }
}
