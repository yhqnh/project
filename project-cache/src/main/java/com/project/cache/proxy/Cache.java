package com.project.cache.proxy;

import com.project.cache.support.CachePara;

public interface Cache {

	public Object parseCacheValue(CachePara cachePara, Object fromCache);

	public void putValue2Cache(CachePara cachePara, Object result) throws Throwable;
}
