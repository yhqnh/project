package com.project.cache.simple;

import javax.annotation.Resource;

import com.project.cache.BaseCache;
import com.project.cache.FloatCache;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.project.cache.factory.Cache;
import com.project.cache.support.CachePara;
import com.project.cache.support.RedisClientTemplate;

@Component
@Slf4j
public class SimpleFloatCache extends FloatCache implements Cache {


    public void putValue2Cache(CachePara cachePara, Object result) throws Throwable {
        Float putResult = (Float) result;
        String key = (String) cachePara.getObjectKey();
        if (!StringUtils.isEmpty(putResult)) {
            if (cachePara.isSerializable()) {
                template.set(defaultSerializer.serialize(key), defaultSerializer.serialize(putResult), cachePara.expire);
            } else {
                template.set(key, Float.toString(putResult), cachePara.expire);
            }
        }
    }
}
