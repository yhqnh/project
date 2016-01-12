package com.project.cache;

import com.project.cache.support.CachePara;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/1/12.
 */
public class CharCache extends BaseCache {

    public Object parseCacheValue(CachePara cachePara, Object fromCache) {
        Character result = null;
        if (cachePara.isSerializable()) {
            result = (Character) defaultSerializer.deserialize((byte[]) fromCache);
        } else {
            result = ((String) fromCache).toCharArray()[0];
        }
        return result;
    }
}
