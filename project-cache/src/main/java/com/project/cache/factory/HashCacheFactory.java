package com.project.cache.factory;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.cache.cacher.hash.HashBooleanCacher;
import com.project.cache.cacher.hash.HashByteCacher;
import com.project.cache.cacher.hash.HashCharCacher;
import com.project.cache.cacher.hash.HashDoubleCacher;
import com.project.cache.cacher.hash.HashFloatCacher;
import com.project.cache.cacher.hash.HashIntegerCacher;
import com.project.cache.cacher.hash.HashLongCacher;
import com.project.cache.cacher.hash.HashObjectCacher;
import com.project.cache.cacher.hash.HashStringCacher;

@Component
@Slf4j
public class HashCacheFactory extends CacheFactory{

	@Autowired
	private HashStringCacher stringCacher;
	
	@Autowired
	private HashIntegerCacher integerCacher;
	
	@Autowired
	private HashLongCacher longCacher;
	
	@Autowired
	private HashFloatCacher floatCacher;
	
	@Autowired
	private HashDoubleCacher doubleCacher;
	
	@Autowired
	private HashBooleanCacher booleanCacher;
	
	@Autowired
	private HashByteCacher byteCacher;
	
	@Autowired
	private HashCharCacher charCacher;
	
	@Autowired
	private HashObjectCacher objectCacher;
	
	@Override
	public Cache create(Class returnClass) {
		
		String simpleName = returnClass.getSimpleName();
		log.debug("method return type:" + simpleName);
		if(simpleName.equals("String")){
			return this.stringCacher;
		}
		
		if(simpleName.equals("int") || simpleName.equals("Integer")){
			return this.integerCacher;
		}
		
		if(simpleName.equals("long") || simpleName.equals("Long")){
			return this.longCacher;
		}
		
		if(simpleName.equals("float") || simpleName.equals("Float")){
			return this.floatCacher;
		}
		
		if(simpleName.equals("double") || simpleName.equals("Double")){
			return this.doubleCacher;
		}
		
		if(simpleName.equals("boolean") || simpleName.equals("Boolean")){
			return this.booleanCacher;
		}
		
		if(simpleName.equals("byte") || simpleName.equals("Byte")){
			return this.byteCacher;
		}
		
		if(simpleName.equals("char") || simpleName.equals("Character")){
			return this.charCacher;
		}
		return this.objectCacher;
	}
}
