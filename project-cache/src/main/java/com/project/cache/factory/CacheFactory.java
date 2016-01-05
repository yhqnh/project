package com.project.cache.factory;

public abstract class CacheFactory {

	public abstract Cache create(Class returnClass);
}
