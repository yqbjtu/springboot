package com.yq.util;

/**
 * Simple to Introduction
 * className: ClassLoadDemo
 *
 * @author EricYang
 * @version 2019/4/21 21:05
 */

/*
注：AppClassLoader 和 ExtClassLoader 由 Java 编写并且都是 java.lang.ClassLoader 的子类，
而 BootstarapClassLoader 并非由 Java 实现而是由C++ 实现，所以打印结果为null。
 */
public class ClassLoadDemo {
    public static void main(String[] args) {
        System.out.println(ClassLoadDemo.class.getClassLoader());
        System.out.println(ClassLoadDemo.class.getClassLoader().getParent());
        System.out.println(ClassLoadDemo.class.getClassLoader().getParent().getParent());
    }
}
