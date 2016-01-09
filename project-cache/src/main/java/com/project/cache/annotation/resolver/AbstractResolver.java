package com.project.cache.annotation.resolver;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

@Slf4j
public abstract class AbstractResolver {

	protected byte[] rawKey(String key) {
		return key.getBytes();
	}

	/**
	 * 获取返回值类型）
	 * 
	 * @param returnType
	 * @return 0:string,int,long,float,double,boolean,byte 1:其他
	 */
	protected Integer getReturnType(Class returnClass) {

		String simpleName = returnClass.getSimpleName();
		if (simpleName.equals("String")) {
			return 0;
		}

		if (simpleName.equals("int") || simpleName.equals("Integer")) {
			return 0;
		}

		if (simpleName.equals("long") || simpleName.equals("Long")) {
			return 0;
		}

		if (simpleName.equals("float") || simpleName.equals("Float")) {
			return 0;
		}

		if (simpleName.equals("double") || simpleName.equals("Double")) {
			return 0;
		}

		if (simpleName.equals("boolean") || simpleName.equals("Boolean")) {
			return 0;
		}

		if (simpleName.equals("byte") || simpleName.equals("Byte")) {
			return 0;
		}

		if (simpleName.equals("char")) {
			return 0;
		}

		return 1;
	}

	/**
	 * 获取被阻碍办法对象 MethodSignature.getMethod() 获取的是顶层接口或者父类的办法对象 而缓存的注解在实现类的办法上
	 * 所以应当应用反射获取当前对象的办法对象
	 */
	protected Method getMethod(ProceedingJoinPoint pjp) {
		// 获取参数的类型
		Object[] args = pjp.getArgs();
		Class[] argTypes = new Class[pjp.getArgs().length];
		for (int i = 0; i < args.length; i++) {
			argTypes[i] = args[i].getClass();
		}
		Method method = null;
		try {
			method = pjp.getTarget().getClass()
					.getMethod(pjp.getSignature().getName(), argTypes);
		} catch (NoSuchMethodException e) {
			log.error("NoSuchMethodException", e);
		} catch (SecurityException e) {
			log.error("SecurityException", e);
		}
		return method;
	}

	/**
	 * 获取缓存的key key 定义在注解上.支撑SPEL表达式
	 * 
	 * @param pjp
	 * @return
	 */
	protected String parseSpel(String spel, Method method, Object[] args) {

		if (StringUtils.isEmpty(spel)) {
			return spel;
		}

		if (!spel.startsWith("#")) {
			return spel;
		}

		// 获取被阻碍办法参数名列表(应用Spring支撑类库)
		LocalVariableTableParameterNameDiscoverer discover = new LocalVariableTableParameterNameDiscoverer();

		String[] paraNameArr = discover.getParameterNames(method);

		// 应用SPEL进行key的解析
		ExpressionParser parser = new SpelExpressionParser();

		// SPEL高低文
		StandardEvaluationContext context = new StandardEvaluationContext();

		// 把办法参数放入SPEL高低文中
		for (int i = 0; i < paraNameArr.length; i++) {
			if (getReturnType(method.getReturnType()).compareTo(1) == 0) {
				context.setVariable(paraNameArr[i], args[i]);
			} else {
				context.setRootObject(args[i]);
			}
			return parser.parseExpression(spel).getValue(context, String.class);
		}
		return parser.parseExpression(spel).getValue(String.class);
	}
}
