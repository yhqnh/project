package com.project.cache;

import com.project.cache.support.CachePara;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/1/12.
 */
public class LongCache extends BaseCache {

    public Object parseCacheValue(CachePara cachePara, Object fromCache) {
        Long result = null;
        if (cachePara.isSerializable()) {
            result = (Long) defaultSerializer.deserialize((byte[]) fromCache);
        } else {
            result = Long.parseLong((String) fromCache);
        }
        return result;
    }

}
