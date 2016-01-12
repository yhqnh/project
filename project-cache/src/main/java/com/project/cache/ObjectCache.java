package com.project.cache;

import com.project.cache.support.CachePara;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/1/12.
 */
public class ObjectCache extends BaseCache {

    public Object parseCacheValue(CachePara cachePara, Object fromCache) {
        Object result = null;
        if (cachePara.isSerializable()) {
            result = defaultSerializer.deserialize((byte[]) fromCache);
        } else {
            result = fromCache;
        }
        return result;
    }
}
