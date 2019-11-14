package com.atkehui.spring.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class CalculatorLogProxy implements InvocationHandler {
    
    private Object target; // 代理的目标对象
    
    public CalculatorLogProxy(Object target){
        this.target = target;
    }

    /* 
     * method：代理方法
     * args：代理方法的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("打印日志开始：" + method.getName() + "(" + Arrays.asList(args) + ")");
        Object result = method.invoke(target, args);
        System.out.println("打印日志结束：" + method.getName() + "(" + Arrays.asList(args) + ")");
        return result;
    }
    
    public static Object createInstance(Object target){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
                target.getClass().getInterfaces(), new CalculatorLogProxy(target));
    }
}
