package com.project.cache.support;

import lombok.Data;

import org.aspectj.lang.ProceedingJoinPoint;

@Data
public class CachePara {

	public ProceedingJoinPoint pjp;
	
	public Object objectKey;
	
	public Object objectField;
	
	public int expire;
}
