package com.project.cache;

import com.project.cache.support.CachePara;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/1/12.
 */
public class StringCache extends BaseCache {

    public Object parseCacheValue(CachePara cachePara, Object fromCache) {
        String result = null;
        if (cachePara.isSerializable()) {
            result = (String) defaultSerializer.deserialize((byte[]) fromCache);
        } else {
            result = (String) fromCache;
        }
        return result;
    }
}
