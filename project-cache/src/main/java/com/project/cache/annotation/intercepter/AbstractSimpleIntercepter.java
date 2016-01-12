package com.project.cache.annotation.intercepter;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;

@Slf4j
public abstract class AbstractSimpleIntercepter extends AbstractIntercepter {

	public abstract Object simpleCacheable(ProceedingJoinPoint pjp) throws Throwable;

	public abstract Object simpleCacheEvict(ProceedingJoinPoint pjp) throws Throwable;

	public abstract Object simpleCachePut(ProceedingJoinPoint pjp) throws Throwable;
	
}
