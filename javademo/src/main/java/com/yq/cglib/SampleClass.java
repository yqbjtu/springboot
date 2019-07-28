package com.yq.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Simple to Introduction
 * className: SampleClass
 *
 * @author EricYang
 * @version 2019/7/28 21:24
 */

public class SampleClass {

    public String test(String input){
        return "hello world";
    }

    public static void main(String[] args) {
        SampleClass sample =new SampleClass();
        sample.testFixedValue();
        //sample.testMethodInterceptor();

    }

    private void testFixedValue() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        //FixedValue用来对所有拦截的方法返回相同的值，从输出我们可以看出来，Enhancer对非final方法test()、toString()、hashCode()进行了拦截，
        // 没有对getClass进行拦截。由于hashCode()方法需要返回一个Number，但是我们返回的是一个String，这解释了上面的程序中为什么会抛出异常。

        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "Hello cglib";
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        System.out.println("---call test method---");
        System.out.println(proxy.test(null)); //拦截test，输出Hello cglib
        System.out.println("---call toString method---");
        System.out.println(proxy.toString());
        System.out.println("---call getClass method---");
        System.out.println(proxy.getClass());
        System.out.println(proxy.hashCode());
    }

    private void testMethodInterceptor( ) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before method run...");
                Object result = proxy.invokeSuper(obj, args);
                System.out.println("after method run...");
                return result;
            }
        });

        SampleClass sample = (SampleClass) enhancer.create();
        System.out.println("---call test method---");
        sample.test(null);
        System.out.println("---call toString method---");
        sample.toString();
        //Enhancer创建一个被代理对象的子类并且拦截所有的方法调用（包括从Object中继承的toString和hashCode方法）。
        // Enhancer不能够拦截final方法，例如Object.getClass()方法
        System.out.println("---call getClass method---");
        sample.getClass();
    }
}