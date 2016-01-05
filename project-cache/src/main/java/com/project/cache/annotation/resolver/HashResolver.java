package com.project.cache.annotation.resolver;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.project.cache.annotation.HashCacheable;
import com.project.cache.factory.Cache;
import com.project.cache.factory.HashCacheFactory;
import com.project.cache.support.CachePara;

/**
 * @author yanghuqiang
 * @email yhqnh@aliyun.com 2015年6月4日下午3:04:53
 */
@Component
@Aspect
@Slf4j
public class HashResolver extends AbstractSimpleResolver{
	
	
	@Resource
	HashCacheFactory cacheFactory;

	/**
	 * 缓存新增
	 */
	@Around(value = "@annotation(com.project.cache.annotation.HashCacheable)")
	public Object simpleCacheable(ProceedingJoinPoint pjp) throws Throwable {

		CachePara cachePara = get(pjp);
		Class returnClass = ((MethodSignature) pjp.getSignature()).getReturnType();
		Cache cacher = cacheFactory.create(returnClass);
		return cacher.cacheAbled(cachePara);
	}

	/**
	 * 缓存清除
	 */
	@Around(value = "@annotation(com.project.cache.annotation.HashCacheEvict)")
	public Object simpleCacheEvict(ProceedingJoinPoint pjp) throws Throwable{

		CachePara cachePara = get(pjp);
		Class returnClass = ((MethodSignature) pjp.getSignature()).getReturnType();
		Cache cacher = cacheFactory.create(returnClass);
		return cacher.cacheEvict(cachePara);
	}
	
	/**
	 * 缓存更新
	 */
	@Around(value = "@annotation(com.project.cache.annotation.HashCachePut)")
	public Object simpleCachePut(ProceedingJoinPoint pjp) throws Throwable{
		CachePara cachePara = get(pjp);
		Class returnClass = ((MethodSignature) pjp.getSignature()).getReturnType();
		Cache cacher = cacheFactory.create(returnClass);
		return cacher.cachePut(cachePara);
	}
	
	
	private CachePara get(ProceedingJoinPoint pjp){
		
		Method method = getMethod(pjp);
		if(StringUtils.isEmpty(method)){
			return null;
		}
		
		CachePara cachePara = new CachePara();
		HashCacheable cache = method.getAnnotation(HashCacheable.class);
		String key = parseKey(cache.key(), method, pjp.getArgs());
		String filed = parseKey(cache.field(), method, pjp.getArgs());
		int expire = cache.expire();
		
		cachePara.setObjectKey(key);
		cachePara.setObjectField(filed);
		cachePara.setExpire(expire);
		cachePara.setPjp(pjp);
		return cachePara;
	}
	
}
