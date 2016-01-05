package com.project.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yanghuqiang
 * @email yhqnh@aliyun.com 2015年11月3日 下午5:45:56
 * @单位：秒
 * @0永不过期
 * @小于0或空，使用默认过期时间
 * @大于0，对应具体数值
 * @return
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HashCacheable {

	String key();

    String field();

    int expire() default -1;
}
