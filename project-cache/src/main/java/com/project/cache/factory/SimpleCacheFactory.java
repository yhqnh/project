package com.project.cache.factory;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.cache.cacher.simple.SimpleBooleanCacher;
import com.project.cache.cacher.simple.SimpleByteCacher;
import com.project.cache.cacher.simple.SimpleCharCacher;
import com.project.cache.cacher.simple.SimpleDoubleCacher;
import com.project.cache.cacher.simple.SimpleFloatCacher;
import com.project.cache.cacher.simple.SimpleIntegerCacher;
import com.project.cache.cacher.simple.SimpleLongCacher;
import com.project.cache.cacher.simple.SimpleObjectCacher;
import com.project.cache.cacher.simple.SimpleStringCacher;

@Component
@Slf4j
public class SimpleCacheFactory extends CacheFactory{

	@Autowired
	private SimpleStringCacher stringCacher;
	
	@Autowired
	private SimpleIntegerCacher integerCacher;
	
	@Autowired
	private SimpleLongCacher longCacher;
	
	@Autowired
	private SimpleFloatCacher floatCacher;
	
	@Autowired
	private SimpleDoubleCacher doubleCacher;
	
	@Autowired
	private SimpleBooleanCacher booleanCacher;
	
	@Autowired
	private SimpleByteCacher byteCacher;
	
	@Autowired
	private SimpleCharCacher charCacher;
	
	@Autowired
	private SimpleObjectCacher objectCacher;
	
	@Override
	public Cache create(Class returnClass) {
		
		String simpleName = returnClass.getSimpleName();
		log.debug("returnClass simpleName:" + simpleName);
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
