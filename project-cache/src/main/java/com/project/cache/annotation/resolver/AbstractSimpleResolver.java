package com.project.cache.annotation.resolver;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StringUtils;

import com.project.cache.annotation.SimpleCacheable;
import com.project.cache.support.CachePara;

@Slf4j
public abstract class AbstractSimpleResolver extends AbstractResolver{

	public abstract Object simpleCacheable(ProceedingJoinPoint pjp) throws Throwable;

	public abstract Object simpleCacheEvict(ProceedingJoinPoint pjp) throws Throwable;

	public abstract Object simpleCachePut(ProceedingJoinPoint pjp) throws Throwable;
	
}
