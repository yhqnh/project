package com.project.cache.simple;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.project.cache.BaseCache;
import com.project.cache.ObjectCache;
import com.project.common.util.JsonFormatterUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.project.cache.factory.Cache;
import com.project.cache.support.CachePara;
import com.project.cache.support.RedisClientTemplate;

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
