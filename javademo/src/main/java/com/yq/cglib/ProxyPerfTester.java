package com.yq.cglib;

import com.yq.cglib.impl.ClazzAImpl;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Simple to Introduction
 * className: ProxyPerfTester
 *
 * @author EricYang
 * @version 2019/7/28 21:14
 */
public class ProxyPerfTester {
    public static void main(String[] args) {
        //创建测试对象；
        ClazzA nativeTest = new ClazzAImpl();
        ClazzA cglibProxy = CglibProxyTest.newProxyInstance(ClazzAImpl.class);

        //预热一下；
        int preRunCount = 10000;
        runWithoutMonitor(cglibProxy, preRunCount);

        //执行测试；
        Map<String, ClazzA> tests = new LinkedHashMap<>();
        tests.put("Native   ", nativeTest);
        tests.put("Cglib    ", cglibProxy);
        int repeatCount = 3;
        int runCount = 1000000;
        runTest(repeatCount, runCount, tests);
        runCount = 50000000;
        runTest(repeatCount, runCount, tests);
    }

    private static void runTest(int repeatCount, int runCount, Map<String, ClazzA> tests){
        System.out.println(String.format("\n==================== run test : [repeatCount=%s] [runCount=%s] [java.version=%s] ====================", repeatCount, runCount, System.getProperty("java.version")));
        for (int i = 0; i < repeatCount; i++) {
            System.out.println(String.format("\n--------- test : [%s] ---------", (i+1)));
            for (String key : tests.keySet()) {
                runWithMonitor(tests.get(key), runCount, key);
            }
        }
    }

    private static void runWithoutMonitor(ClazzA test, int runCount) {
        for (int i = 0; i < runCount; i++) {
            test.method01Add(i);
        }
    }

    private static void runWithMonitor(ClazzA test, int runCount, String tag) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < runCount; i++) {
            test.method01Add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("["+tag + "] Elapsed Time:" + (end-start) + "ms");
    }
}
