package com.yq;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by EricYang on 2019/7/13.
 */
@Slf4j
public class GcMainDemo {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        for(int i=0;i < 100; i++) {
            stringList.add(new String("The " + i + "th string"));
        }

        //unit bytes
        long maxMemBefore = Runtime.getRuntime().freeMemory();
        //System.gc();
        Runtime.getRuntime().gc();
        long maxMemAfter = Runtime.getRuntime().freeMemory();
        log.info("System.gc(), maxMemBefore={}, maxMemAfter={}, diff={}kb", maxMemBefore, maxMemAfter, (maxMemAfter - maxMemBefore)/1024);
        for(int i=0;i < 100; i++) {
            stringList.add(new String("The " + i + "th string"));
        }
        maxMemBefore = Runtime.getRuntime().freeMemory();
        Runtime.getRuntime().gc();
        maxMemAfter = Runtime.getRuntime().freeMemory();
        log.info("Runtime.getRuntime().gc(), maxMemBefore={}, maxMemAfter={}, diff={}kb", maxMemBefore, maxMemAfter, (maxMemAfter - maxMemBefore)/1024);
        try {
            TimeUnit.SECONDS.sleep(2000);
        } catch (Exception ex) {
            log.error("sleep exception", ex);
        }
    }
}
