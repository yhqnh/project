package com.project.cache.annotation.resolver;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;

@Slf4j
public abstract class AbstractHashResolver extends AbstractResolver{

	public abstract Object stringCacheable(ProceedingJoinPoint pjp);

	public abstract Object stringCacheEvict(ProceedingJoinPoint pjp);

	public abstract Object stringCachePut(ProceedingJoinPoint pjp);
}
