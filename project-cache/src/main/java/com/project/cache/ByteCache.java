package com.project.cache;

import com.project.cache.support.CachePara;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/1/12.
 */
public class ByteCache extends BaseCache {

    public Object parseCacheValue(CachePara cachePara, Object fromCache) {
        Byte result = null;
        if (cachePara.isSerializable()) {
            result = (Byte) defaultSerializer.deserialize((byte[]) fromCache);
        } else {
            result = Byte.parseByte((String) fromCache);
        }
        return result;
    }
}
