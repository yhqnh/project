package com.project.cache.annotation.intercepter;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;

@Slf4j
public abstract class AbstractHashIntercepter extends AbstractIntercepter {

	public abstract Object hashCacheable(ProceedingJoinPoint pjp) throws Throwable;

	public abstract Object hashCacheEvict(ProceedingJoinPoint pjp) throws Throwable;

	public abstract Object hashCachePut(ProceedingJoinPoint pjp) throws Throwable;
}
