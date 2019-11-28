package com.yq.gc;

/**
 * Simple to Introduction
 * className: AllocationOnStack
 * 开启逃逸模式，关闭线程本地缓存模式（TLAB）（jdk1.8默认开启）
 * * -Xmx10m -Xms10m  -XX:+DoEscapeAnalysis  -XX:-UseTLAB  -XX:+PrintGC
 * -Xmx10m -Xms10m -XX:-DoEscapeAnalysis -XX:+UseTLAB -XX:+PrintGC
 * @author EricYang
 * @version 2019/9/21 12:46
 */

public class AllocationOnStack {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        for (int index = 0; index < 100000000; index++) {
            allocate();
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start)+ " ms");
        Thread.sleep(1000*1000);
    }

    private static void allocate() {
        byte[] bytes = new byte[2];
        bytes[0] = 1;
        bytes[1] = 1;
    }
}