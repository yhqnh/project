package com.project.cache.annotation.intercepter;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import com.project.cache.annotation.SimpleCacheEvict;
import com.project.cache.annotation.SimpleCachePut;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.project.cache.annotation.SimpleCacheable;
import com.project.cache.factory.Cache;
import com.project.cache.factory.SimpleCacheProxy;
import com.project.cache.support.CachePara;

/**
 * @author yanghuqiang
 * @email yhqnh@aliyun.com 2015年11月3日 下午5:35:12
 */
@Component
@Aspect
@Slf4j
public class SimpleIntercepter extends AbstractSimpleIntercepter {

	@Resource
	SimpleCacheProxy cacheProxy;

	/**
	 * 缓存新增
	 */
	@Around(value = "@annotation(com.project.cache.annotation.SimpleCacheable)")
	public Object simpleCacheable(ProceedingJoinPoint pjp) throws Throwable {

		CachePara cachePara = getCacheablePara(pjp);
		Class returnClass = ((MethodSignature) pjp.getSignature()).getReturnType();
		cacheProxy.create(returnClass);
		return cacheProxy.cacheAbled(cachePara);
	}

	/**
	 * 缓存清除
	 */
	@Around(value = "@annotation(com.project.cache.annotation.SimpleCacheEvict)")
	public Object simpleCacheEvict(ProceedingJoinPoint pjp) throws Throwable{

		CachePara cachePara = getCacheEvictPara(pjp);
		Class returnClass = ((MethodSignature) pjp.getSignature()).getReturnType();
		cacheProxy.create(returnClass);
		return cacheProxy.cacheEvict(cachePara);
	}
	
	/**
	 * 缓存更新
	 */
	@Around(value = "@annotation(com.project.cache.annotation.SimpleCachePut)")
	public Object simpleCachePut(ProceedingJoinPoint pjp) throws Throwable{
		CachePara cachePara = getCachePutPara(pjp);
		Class returnClass = ((MethodSignature) pjp.getSignature()).getReturnType();
		cacheProxy.create(returnClass);
		return cacheProxy.cachePut(cachePara);
	}
	
	
	private CachePara getCacheablePara(ProceedingJoinPoint pjp){
		
		Method method = getMethod(pjp);
		if(StringUtils.isEmpty(method)){
			return null;
		}
		
		CachePara cachePara = new CachePara();
		SimpleCacheable cache = method.getAnnotation(SimpleCacheable.class);
		String key = parseOgnl(cache.key(), method, pjp.getArgs());
		int expire = cache.expire();
		boolean serializable = cache.serializable();
		
		cachePara.setObjectKey(key);
		cachePara.setExpire(expire);
		cachePara.setPjp(pjp);
		cachePara.setSerializable(serializable);
		return cachePara;
	}


	private CachePara getCachePutPara(ProceedingJoinPoint pjp){

		Method method = getMethod(pjp);
		if(StringUtils.isEmpty(method)){
			return null;
		}

		CachePara cachePara = new CachePara();
		SimpleCachePut cache = method.getAnnotation(SimpleCachePut.class);
		String key = parseOgnl(cache.key(), method, pjp.getArgs());
		int expire = cache.expire();
		boolean serializable = cache.serializable();

		cachePara.setObjectKey(key);
		cachePara.setExpire(expire);
		cachePara.setPjp(pjp);
		cachePara.setSerializable(serializable);
		return cachePara;
	}


	private CachePara getCacheEvictPara(ProceedingJoinPoint pjp){

		Method method = getMethod(pjp);
		if(StringUtils.isEmpty(method)){
			return null;
		}

		CachePara cachePara = new CachePara();
		SimpleCacheEvict cache = method.getAnnotation(SimpleCacheEvict.class);
		String key = parseOgnl(cache.key(), method, pjp.getArgs());

		cachePara.setObjectKey(key);
		cachePara.setPjp(pjp);
		return cachePara;
	}
}
