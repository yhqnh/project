package com.project.cache;

import com.project.cache.support.CachePara;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/1/12.
 */
public class IntegerCache extends BaseCache {

    public Object parseCacheValue(CachePara cachePara, Object fromCache) {
        Integer result = null;
        if (cachePara.isSerializable()) {
            result = (Integer) defaultSerializer.deserialize((byte[]) fromCache);
        } else {
            result = Integer.parseInt((String) fromCache);
        }
        return result;
    }
}
