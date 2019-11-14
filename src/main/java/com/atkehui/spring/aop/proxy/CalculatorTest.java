package com.atkehui.spring.aop.proxy;

import java.lang.reflect.Method;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CalculatorTest {

    @Test
    public void test() {
        // 初始化目标对象
        Calculator calculator = new CalculatorImpl();
        // 初始化代理对象
        Calculator calculatorProxy = (Calculator)CalculatorLogProxy.createInstance(calculator);
        Calculator calculatorvalidProxy = (Calculator)CalculatorValidProxy.createInstance(calculatorProxy);
        calculatorvalidProxy.dev(100, 0);
    }
    
    @Test
    public void test2() {
        CalculatorImpl calculator = new CalculatorImpl();
        // 创建cglib的核心对象
        Enhancer enhancer = new Enhancer();
        // 设置父类：把CalculatorImpl作为父类
        enhancer.setSuperclass(calculator.getClass());
        // 设置回调
        enhancer.setCallback(new MethodInterceptor() {
            /*
             * 当你调用目标方法时，实质上是调用该方法 
             * intercept四个参数： 
             *  proxy:代理对象
             *  method:目标方法 
             *  args：目标方法的形参
             *  methodProxy:代理方法
             */
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy)
                    throws Throwable {
                // 添加记录的功能
                System.out.println("记录日志开始...");
                // 调用目标(父类)方法
                Object result = methodProxy.invokeSuper(proxy, args);
                System.out.println("记录日志结束...");
                return result;
            }

        });
        // 创建代理对象
        CalculatorImpl proxy = (CalculatorImpl) enhancer.create();
        proxy.add(100, 200);

    }


}
