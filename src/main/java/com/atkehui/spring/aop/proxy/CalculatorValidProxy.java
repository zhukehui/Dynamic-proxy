package com.atkehui.spring.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CalculatorValidProxy implements InvocationHandler{
    
    private Object target;
    
    public CalculatorValidProxy(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("dev") && args != null && args.length >=2) {
            if ((Integer)args[1] == 0) {
                System.out.println("除法的除数不能为0！！");
                return null;
            }
        }
        Object result = method.invoke(target, args);
        return result;
    }
    
    public static Object createInstance(Object target){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
                target.getClass().getInterfaces(), new CalculatorValidProxy(target));
    }

}
