package com.project.cache.annotation.intercepter;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

@Slf4j
public abstract class AbstractIntercepter {

    protected byte[] rawKey(String key) {
        return key.getBytes();
    }

    /**
     * 获取返回值类型）
     *
     * @param returnClass
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
     * @param ognl
     * @return
     */
    protected String parseOgnl(String ognl, Method method, Object[] args) {
        String result = "";

        if (StringUtils.isEmpty(ognl)) {
            return ognl;
        }

        //不是OGNL表达式
        if (!ognl.startsWith("#")) {
            return ognl;
        }

        //是ognl的表达式去掉#号
        ognl = ognl.replaceAll("#", "");

        // 获取被阻碍办法参数名列表(应用Spring支撑类库)
        LocalVariableTableParameterNameDiscoverer discover = new LocalVariableTableParameterNameDiscoverer();

        String[] paraNameArr = discover.getParameterNames(method);

        try {
            OgnlContext context = new OgnlContext();
            // OGNL实现了MAP接口
            for (int i = 0; i < paraNameArr.length; i++) {
                context.put(paraNameArr[i], args[i]);
            }
            Object object = Ognl.getValue(Ognl.parseExpression(ognl), context, context);
            result = object.toString();
        } catch (OgnlException ognlException) {
            log.error("解析OGNL{}出错", ognl, ognlException);
        }
        return result;
    }
}
