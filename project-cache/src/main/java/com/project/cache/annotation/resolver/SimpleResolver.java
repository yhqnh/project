package com.project.cache.annotation.resolver;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.project.cache.annotation.SimpleCacheable;
import com.project.cache.factory.Cache;
import com.project.cache.factory.SimpleCacheFactory;
import com.project.cache.support.CachePara;

/**
 * @author yanghuqiang
 * @email yhqnh@aliyun.com 2015年11月3日 下午5:35:12
 */
@Component
@Aspect
@Slf4j
public class SimpleResolver extends AbstractSimpleResolver{

	@Resource
	SimpleCacheFactory cacheFactory;

	/**
	 * 缓存新增
	 */
	@Around(value = "@annotation(com.project.cache.annotation.SimpleCacheable)")
	public Object simpleCacheable(ProceedingJoinPoint pjp) throws Throwable {

		CachePara cachePara = get(pjp);
		Class returnClass = ((MethodSignature) pjp.getSignature()).getReturnType();
		Cache cacher = cacheFactory.create(returnClass);
		return cacher.cacheAbled(cachePara);
	}

	/**
	 * 缓存清除
	 */
	@Around(value = "@annotation(com.project.cache.annotation.SimpleCacheEvict)")
	public Object simpleCacheEvict(ProceedingJoinPoint pjp) throws Throwable{

		CachePara cachePara = get(pjp);
		Class returnClass = ((MethodSignature) pjp.getSignature()).getReturnType();
		Cache cacher = cacheFactory.create(returnClass);
		return cacher.cacheEvict(cachePara);
	}
	
	/**
	 * 缓存更新
	 */
	@Around(value = "@annotation(com.project.cache.annotation.SimpleCachePut)")
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
		SimpleCacheable cache = method.getAnnotation(SimpleCacheable.class);
		String key = parseSpel(cache.key(), method, pjp.getArgs());
		int expire = cache.expire();
		
		cachePara.setObjectKey(key);
		cachePara.setExpire(expire);
		cachePara.setPjp(pjp);
		return cachePara;
	}
}
