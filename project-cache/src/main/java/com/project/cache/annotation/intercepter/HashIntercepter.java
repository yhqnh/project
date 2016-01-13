package com.project.cache.annotation.intercepter;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.project.cache.annotation.HashCacheEvict;
import com.project.cache.annotation.HashCachePut;
import com.project.cache.annotation.HashCacheable;
import com.project.cache.proxy.CacheProxy;
import com.project.cache.proxy.ProxyTypeEnum;
import com.project.cache.support.CachePara;

/**
 * @author yanghuqiang
 * @email yhqnh@aliyun.com 2015年6月4日下午3:04:53
 */
@Component
@Aspect
@Slf4j
public class HashIntercepter extends AbstractHashIntercepter {


    @Resource
    CacheProxy cacheProxy;

    /**
     * 缓存新增
     */
    @Around(value = "@annotation(com.project.cache.annotation.HashCacheable)")
    public Object hashCacheable(ProceedingJoinPoint pjp) throws Throwable {
        CachePara cachePara = getCacheablePara(pjp);
        cacheProxy.create(cachePara);
        return cacheProxy.cacheAbled(cachePara);
    }

    /**
     * 缓存清除
     */
    @Around(value = "@annotation(com.project.cache.annotation.HashCacheEvict)")
    public Object hashCacheEvict(ProceedingJoinPoint pjp) throws Throwable {

        CachePara cachePara = getCacheEvictPara(pjp);
        cacheProxy.create(cachePara);
        return cacheProxy.cacheEvict(cachePara);
    }

    /**
     * 缓存更新
     */
    @Around(value = "@annotation(com.project.cache.annotation.HashCachePut)")
    public Object hashCachePut(ProceedingJoinPoint pjp) throws Throwable {
        CachePara cachePara = getCachePutPara(pjp);
        cacheProxy.create(cachePara);
        return cacheProxy.cachePut(cachePara);
    }


    private CachePara getCacheablePara(ProceedingJoinPoint pjp) {

        Method method = getMethod(pjp);
        if (StringUtils.isEmpty(method)) {
            return null;
        }

        CachePara cachePara = new CachePara();
        init(cachePara, pjp);

        HashCacheable cache = method.getAnnotation(HashCacheable.class);
        String key = parseOgnl(cache.key(), method, pjp.getArgs());
        String filed = parseOgnl(cache.field(), method, pjp.getArgs());
        int expire = cache.expire();
        boolean serializable = cache.serializable();

        cachePara.setObjectKey(key);
        cachePara.setObjectField(filed);
        cachePara.setExpire(expire);
        cachePara.setPjp(pjp);
        cachePara.setSerializable(serializable);
        return cachePara;
    }


    private CachePara getCachePutPara(ProceedingJoinPoint pjp) {

        Method method = getMethod(pjp);
        if (StringUtils.isEmpty(method)) {
            return null;
        }

        CachePara cachePara = new CachePara();
        init(cachePara, pjp);

        HashCachePut cache = method.getAnnotation(HashCachePut.class);
        String key = parseOgnl(cache.key(), method, pjp.getArgs());
        String filed = parseOgnl(cache.field(), method, pjp.getArgs());
        int expire = cache.expire();
        boolean serializable = cache.serializable();

        cachePara.setObjectKey(key);
        cachePara.setObjectField(filed);
        cachePara.setExpire(expire);
        cachePara.setPjp(pjp);
        cachePara.setSerializable(serializable);
        return cachePara;
    }


    private CachePara getCacheEvictPara(ProceedingJoinPoint pjp) {

        Method method = getMethod(pjp);
        if (StringUtils.isEmpty(method)) {
            return null;
        }

        CachePara cachePara = new CachePara();
        init(cachePara, pjp);

        HashCacheEvict cache = method.getAnnotation(HashCacheEvict.class);
        String key = parseOgnl(cache.key(), method, pjp.getArgs());
        String filed = parseOgnl(cache.field(), method, pjp.getArgs());

        cachePara.setObjectKey(key);
        cachePara.setObjectField(filed);
        cachePara.setPjp(pjp);
        return cachePara;
    }

    private void init(CachePara cachePara, ProceedingJoinPoint pjp){
        Class returnClass = ((MethodSignature) pjp.getSignature()).getReturnType();
        cachePara.setReturnClass(returnClass);
        cachePara.setProxyTypeEnum(ProxyTypeEnum.HASH);
    }
}
