package com.project.cache;

import com.project.cache.support.CachePara;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/1/12.
 */
public class BooleanCache extends BaseCache {

    public Object parseCacheValue(CachePara cachePara, Object fromCache) {
        Boolean result = null;
        if (cachePara.isSerializable()) {
            result = (Boolean) defaultSerializer.deserialize((byte[]) fromCache);
        } else {
            result = Boolean.parseBoolean((String) fromCache);
        }
        return result;
    }
}
