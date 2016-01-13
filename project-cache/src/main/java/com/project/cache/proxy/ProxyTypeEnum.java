package com.project.cache.proxy;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/1/11.
 */
public enum ProxyTypeEnum {

    HASH("hash"),SIMPLE("simple");

    public String name;

    ProxyTypeEnum(String name) {
        this.name = name;
    }
}
