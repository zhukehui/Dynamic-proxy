package com.atkehui.spring.aop.proxy;

public class CalculatorImpl implements Calculator {

    @Override
    public int add(int a, int b) {
        System.out.println("调用了加法");
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        System.out.println("调用了减法");
        return a - b;
    }

    @Override
    public int mul(int a, int b) {
        return a * b;
    }

    @Override
    public int dev(int a, int b) {
        System.out.println("调用了除法");
        return a / b;
    }

}
