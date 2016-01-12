package com.project.cache.support;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/1/9.
 */
public class CacheConfig {

    private Object key;
    private Object value;
    private int expire;
    private boolean serializable = false;

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public boolean isSerializable() {
        return serializable;
    }

    public void setSerializable(boolean serializable) {
        this.serializable = serializable;
    }
}
