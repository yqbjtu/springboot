package com.yq.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple to Introduction
 * className: GCLogDemo
 * （jdk1.8默认开启） 默认使用
 * * -Xmx6m -Xms6m   -XX:+HeapDumpOnOutOfMemoryError  -XX:+PrintGC  -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Xloggc:gc.log
 *  D:\E\workspaceGitub\springboot\javademo\target>java -Xmx6m -Xms6m   -XX:+HeapDumpOnOutOfMemoryError  -XX:+PrintGC  -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Xloggc:gc.log -cp classes com.yq.gc.GCLogDemo
 * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
 * 	at java.lang.AbstractStringBuilder.<init>(AbstractStringBuilder.java:68)
 * 	at java.lang.StringBuilder.<init>(StringBuilder.java:89)
 * 	at com.yq.gc.GCLogDemo.allocate(GCLogDemo.java:33)
 * 	at com.yq.gc.GCLogDemo.main(GCLogDemo.java:23)
 * @author EricYang
 * @version 2019/9/21 12:46
 */

public class GCLogDemo {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        List<String> list =new ArrayList<>();

        for(int i=0; i < 100; i++) {
            for (int index = 0; index < 900000000; index++) {
                allocate(list);
            }
        }

        long end = System.currentTimeMillis();
        System.out.println((end - start)+ " ms");
        Thread.sleep(9000*1000);
    }

    private static void allocate(List<String> list) {
        String str1 = "aaa" + System.currentTimeMillis();
        list.add(str1);
    }
}