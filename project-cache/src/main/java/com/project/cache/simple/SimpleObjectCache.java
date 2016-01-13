package com.project.cache.simple;

import com.google.gson.Gson;
import com.project.cache.ObjectCache;
import com.project.common.util.JsonFormatterUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.project.cache.proxy.Cache;
import com.project.cache.support.CachePara;

@Component
@Slf4j
public class SimpleObjectCache extends ObjectCache implements Cache {

    public void putValue2Cache(CachePara cachePara, Object result) throws Throwable {
        Object putResult = result;
        String key = (String) cachePara.getObjectKey();
        int expire = cachePara.getExpire();
        if (!StringUtils.isEmpty(putResult)) {
            if (cachePara.isSerializable()) {
                template.set(defaultSerializer.serialize(key), defaultSerializer.serialize(putResult), cachePara.expire);
            } else {
                String json = JsonFormatterUtil.jsonFormatter(new Gson().toJson(putResult));
                template.set(key, json, expire);
            }
        }
    }
}
