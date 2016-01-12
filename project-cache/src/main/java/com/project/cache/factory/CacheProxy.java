package com.project.cache.factory;

import com.project.cache.hash.*;
import com.project.cache.simple.*;
import com.project.cache.support.CachePara;
import com.project.cache.support.RedisClientTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Component
@Slf4j
public class CacheProxy {

    protected ThreadLocal<Cache> cache;

    @Autowired
    private HashStringCache hashStringCacher;

    @Autowired
    private HashIntegerCache hashIntegerCacher;

    @Autowired
    private HashLongCache hashLongCacher;

    @Autowired
    private HashFloatCache hashFloatCacher;

    @Autowired
    private HashDoubleCache hashDoubleCacher;

    @Autowired
    private HashBooleanCache hashBooleanCacher;

    @Autowired
    private HashByteCache hashByteCacher;

    @Autowired
    private HashCharCache hashCharCacher;

    @Autowired
    private HashObjectCache hashObjectCacher;

    @Autowired
    private SimpleStringCache simpleStringCacher;

    @Autowired
    private SimpleIntegerCache simpleIntegerCacher;

    @Autowired
    private SimpleLongCache simpleLongCacher;

    @Autowired
    private SimpleFloatCache simpleFloatCacher;

    @Autowired
    private SimpleDoubleCache simpleDoubleCacher;

    @Autowired
    private SimpleBooleanCache simpleBooleanCacher;

    @Autowired
    private SimpleByteCache simpleByteCacher;

    @Autowired
    private SimpleCharCache simpleCharCacher;

    @Autowired
    private SimpleObjectCache simpleObjectCacher;

    @Resource
    protected RedisClientTemplate template;

    @Autowired
    protected RedisSerializer<Object> defaultSerializer;

    public void create(CachePara cachePara) {
        if (cachePara.ProxyTypeEnum.equals(ProxyTypeEnum.HASH)) {
            createHashCache(cachePara);
        } else {
            createSimpleCache(cachePara);
        }
    }

    public void createHashCache(CachePara cachePara) {
        Class returnClass = cachePara.getReturnClass();
        String simpleName = returnClass.getSimpleName();
        log.debug("method return type:" + simpleName);
        if (simpleName.equals("String")) {
            cache.set(this.hashStringCacher);
        }

        if (simpleName.equals("int") || simpleName.equals("Integer")) {
            cache.set(this.hashIntegerCacher);
        }

        if (simpleName.equals("long") || simpleName.equals("Long")) {
            cache.set(this.hashLongCacher);
        }

        if (simpleName.equals("float") || simpleName.equals("Float")) {
            cache.set(this.hashFloatCacher);
        }

        if (simpleName.equals("double") || simpleName.equals("Double")) {
            cache.set(this.hashDoubleCacher);
        }

        if (simpleName.equals("boolean") || simpleName.equals("Boolean")) {
            cache.set(this.hashBooleanCacher);
        }

        if (simpleName.equals("byte") || simpleName.equals("Byte")) {
            cache.set(this.hashByteCacher);
        }

        if (simpleName.equals("char") || simpleName.equals("Character")) {
            cache.set(this.hashCharCacher);
        }
        cache.set(this.hashObjectCacher);
    }

    public void createSimpleCache(CachePara cachePara) {
        Class returnClass = cachePara.getReturnClass();
        String simpleName = returnClass.getSimpleName();
        log.debug("returnClass simpleName:" + simpleName);
        if (simpleName.equals("String")) {
            cache.set(this.simpleStringCacher);
        }

        if (simpleName.equals("int") || simpleName.equals("Integer")) {
            cache.set(this.simpleIntegerCacher);
        }

        if (simpleName.equals("long") || simpleName.equals("Long")) {
            cache.set(this.simpleLongCacher);
        }

        if (simpleName.equals("float") || simpleName.equals("Float")) {
            cache.set(this.simpleFloatCacher);
        }

        if (simpleName.equals("double") || simpleName.equals("Double")) {
            cache.set(this.simpleDoubleCacher);
        }

        if (simpleName.equals("boolean") || simpleName.equals("Boolean")) {
            cache.set(this.simpleBooleanCacher);
        }

        if (simpleName.equals("byte") || simpleName.equals("Byte")) {
            cache.set(this.simpleByteCacher);
        }

        if (simpleName.equals("char") || simpleName.equals("Character")) {
            cache.set(this.simpleCharCacher);
        }
        cache.set(this.simpleObjectCacher);
    }

    protected Object getFormCache(CachePara cachePara) {
        String key = (String) cachePara.getObjectKey();
        Object fromCache = null;
        if (cachePara.isSerializable()) {
            fromCache = template.get(defaultSerializer.serialize(key));
        } else {
            fromCache = template.get(key);
        }
        return fromCache;
    }

    public Object cacheAbled(CachePara cachePara) throws Throwable {
        Object fromCache = getFormCache(cachePara);
        Object result = null;
        if (StringUtils.isEmpty(fromCache)) {
            result = cachePara.getPjp().proceed();
            cache.get().putValue2Cache(cachePara, result);
        } else {
            result = cache.get().parseCacheValue(cachePara, fromCache);
        }
        return result;
    }

    public Object cacheEvict(CachePara cachePara) throws Throwable {
        Object fromCache = getFormCache(cachePara);
        if (!StringUtils.isEmpty(fromCache)) {
            String key = (String) cachePara.getObjectKey();
            if (cachePara.isSerializable()) {
                if (cachePara.ProxyTypeEnum.equals(ProxyTypeEnum.HASH)) {
                    template.hdel(defaultSerializer.serialize(key));
                } else {
                    template.del(defaultSerializer.serialize(key));
                }
            } else {
                if (cachePara.ProxyTypeEnum.equals(ProxyTypeEnum.HASH)) {
                    template.hdel(key);
                } else {
                    template.del(key);
                }
            }
        }
        Object result = cache.get().parseCacheValue(cachePara, fromCache);
        ;
        return result;
    }

    public Object cachePut(CachePara cachePara) throws Throwable {
        return cacheAbled(cachePara);
    }

}
