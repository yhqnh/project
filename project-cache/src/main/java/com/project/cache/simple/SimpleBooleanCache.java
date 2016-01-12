package com.project.cache.simple;

import com.project.cache.BaseCache;
import com.project.cache.BooleanCache;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.project.cache.factory.Cache;
import com.project.cache.support.CachePara;

@Component
@Slf4j
public class SimpleBooleanCache extends BooleanCache implements Cache {

    public void putValue2Cache(CachePara cachePara, Object result) throws Throwable {
        Boolean putResult = (Boolean) result;
        String key = (String) cachePara.getObjectKey();
        if (!StringUtils.isEmpty(putResult)) {
            if (cachePara.isSerializable()) {
                template.set(defaultSerializer.serialize(key), defaultSerializer.serialize(putResult), cachePara.expire);
            } else {
                template.set(key, Boolean.toString(putResult), cachePara.expire);
            }
        }
    }
}
