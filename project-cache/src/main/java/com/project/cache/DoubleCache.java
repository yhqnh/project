package com.project.cache;

import com.project.cache.support.CachePara;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/1/12.
 */
public class DoubleCache extends BaseCache {

    public Object parseCacheValue(CachePara cachePara, Object fromCache) {
        Double result = null;
        if (cachePara.isSerializable()) {
            result = (Double) defaultSerializer.deserialize((byte[]) fromCache);
        } else {
            result = Double.parseDouble((String) fromCache);
        }
        return result;
    }
}
