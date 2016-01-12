package com.project.cache.factory;

import com.project.cache.support.CachePara;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.cache.hash.HashBooleanCache;
import com.project.cache.hash.HashByteCache;
import com.project.cache.hash.HashCharCache;
import com.project.cache.hash.HashDoubleCache;
import com.project.cache.hash.HashFloatCache;
import com.project.cache.hash.HashIntegerCache;
import com.project.cache.hash.HashLongCache;
import com.project.cache.hash.HashObjectCache;
import com.project.cache.hash.HashStringCache;

@Component
@Slf4j
public class HashCacheProxy extends CacheProxy implements Cache{

	@Autowired
	private HashStringCache hashStringCacher;
	
	@Autowired
	private HashIntegerCache HashIntegerCacher;
	
	@Autowired
	private HashLongCache HashLongCacher;
	
	@Autowired
	private HashFloatCache HashFloatCacher;
	
	@Autowired
	private HashDoubleCache HashDoubleCacher;
	
	@Autowired
	private HashBooleanCache HashBooleanCacher;
	
	@Autowired
	private HashByteCache HashByteCacher;
	
	@Autowired
	private HashCharCache HashCharCacher;
	
	@Autowired
	private HashObjectCache HashObjectCacher;

	@Override
	public void create(CachePara cachePara) {
		Class returnClass = cachePara.getReturnClass();
		String simpleName = returnClass.getSimpleName();
		log.debug("method return type:" + simpleName);
		if(simpleName.equals("String")){
			cache.set(this.hashStringCacher);
		}
		
		if(simpleName.equals("int") || simpleName.equals("Integer")){
			cache.set(this.HashIntegerCacher);
		}
		
		if(simpleName.equals("long") || simpleName.equals("Long")){
			cache.set(this.HashLongCacher);
		}
		
		if(simpleName.equals("float") || simpleName.equals("Float")){
			cache.set(this.HashFloatCacher);
		}
		
		if(simpleName.equals("double") || simpleName.equals("Double")){
			cache.set(this.HashDoubleCacher);
		}
		
		if(simpleName.equals("boolean") || simpleName.equals("Boolean")){
			cache.set(this.HashBooleanCacher);
		}
		
		if(simpleName.equals("byte") || simpleName.equals("Byte")){
			cache.set(this.HashByteCacher);
		}
		
		if(simpleName.equals("char") || simpleName.equals("Character")){
			cache.set(this.HashCharCacher);
		}
		cache.set(this.HashObjectCacher);
	}
}
