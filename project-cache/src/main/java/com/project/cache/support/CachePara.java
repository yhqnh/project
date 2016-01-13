package com.project.cache.support;

import com.project.cache.proxy.ProxyTypeEnum;
import org.aspectj.lang.ProceedingJoinPoint;

public class CachePara {

	public ProceedingJoinPoint pjp;
	
	public Object objectKey;

	public Object objectField;

	public boolean serializable = false;

	public ProxyTypeEnum ProxyTypeEnum;
	
	public int expire;

	public Class returnClass;

	public ProceedingJoinPoint getPjp() {
		return pjp;
	}

	public void setPjp(ProceedingJoinPoint pjp) {
		this.pjp = pjp;
	}

	public Object getObjectKey() {
		return objectKey;
	}

	public void setObjectKey(Object objectKey) {
		this.objectKey = objectKey;
	}

	public Object getObjectField() {
		return objectField;
	}

	public void setObjectField(Object objectField) {
		this.objectField = objectField;
	}

	public boolean isSerializable() {
		return serializable;
	}

	public void setSerializable(boolean serializable) {
		this.serializable = serializable;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public com.project.cache.proxy.ProxyTypeEnum getProxyTypeEnum() {
		return ProxyTypeEnum;
	}

	public void setProxyTypeEnum(com.project.cache.proxy.ProxyTypeEnum proxyTypeEnum) {
		ProxyTypeEnum = proxyTypeEnum;
	}

	public Class getReturnClass() {
		return returnClass;
	}

	public void setReturnClass(Class returnClass) {
		this.returnClass = returnClass;
	}
}
