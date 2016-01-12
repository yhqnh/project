package com.project.cache.factory;

import com.project.cache.support.CachePara;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.cache.simple.SimpleBooleanCache;
import com.project.cache.simple.SimpleByteCache;
import com.project.cache.simple.SimpleCharCache;
import com.project.cache.simple.SimpleDoubleCache;
import com.project.cache.simple.SimpleFloatCache;
import com.project.cache.simple.SimpleIntegerCache;
import com.project.cache.simple.SimpleLongCache;
import com.project.cache.simple.SimpleObjectCache;
import com.project.cache.simple.SimpleStringCache;

@Component
@Slf4j
public class SimpleCacheProxy extends CacheProxy {

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
	
	@Override
	public void create(CachePara cachePara) {
		Class returnClass = cachePara.getReturnClass();
		
		String simpleName = returnClass.getSimpleName();
		log.debug("returnClass simpleName:" + simpleName);
		if(simpleName.equals("String")){
			cache.set(this.simpleStringCacher);
		}
		
		if(simpleName.equals("int") || simpleName.equals("Integer")){
			cache.set(this.simpleIntegerCacher);
		}
		
		if(simpleName.equals("long") || simpleName.equals("Long")){
			cache.set(this.simpleLongCacher);
		}
		
		if(simpleName.equals("float") || simpleName.equals("Float")){
			cache.set(this.simpleFloatCacher);
		}
		
		if(simpleName.equals("double") || simpleName.equals("Double")){
			cache.set(this.simpleDoubleCacher);
		}
		
		if(simpleName.equals("boolean") || simpleName.equals("Boolean")){
			cache.set(this.simpleBooleanCacher);
		}
		
		if(simpleName.equals("byte") || simpleName.equals("Byte")){
			cache.set(this.simpleByteCacher);
		}
		
		if(simpleName.equals("char") || simpleName.equals("Character")){
			cache.set(this.simpleCharCacher);
		}
		cache.set(this.simpleObjectCacher);
	}
}
