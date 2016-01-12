package com.project.cache;

import com.project.cache.support.CachePara;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/1/12.
 */
public class FloatCache extends BaseCache {

    public Object parseCacheValue(CachePara cachePara, Object fromCache) {
        Float result = null;
        if (cachePara.isSerializable()) {
            result = (Float) defaultSerializer.deserialize((byte[]) fromCache);
        } else {
            result = Float.parseFloat((String) fromCache);
        }
        return result;
    }
}
