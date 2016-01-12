package com.project.cache;

import com.project.cache.support.CacheConfig;
import com.project.cache.support.CachePara;
import com.project.cache.support.RedisClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.Resource;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/1/9.
 */
public class BaseCache {

    @Resource
    protected RedisClientTemplate template;

    @Autowired
    protected RedisSerializer<Object> defaultSerializer;

}
