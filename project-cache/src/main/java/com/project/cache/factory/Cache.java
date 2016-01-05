package com.project.cache.factory;

import com.project.cache.support.CachePara;

public interface Cache {

	public abstract Object cacheAbled(CachePara cachePara) throws Throwable;

	public abstract Object cacheEvict(CachePara cachePara) throws Throwable;

	public abstract Object cachePut(CachePara cachePara) throws Throwable;
}
