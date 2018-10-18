package com.yq;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Simple to Introduction
 * className: PriorityDemo
 *
 * @author EricYang
 * @version 2018/10/17 11:21
 */
@Slf4j
public class PriorityDemo implements Runnable, Comparable<PriorityDemo> {
    private long rts;
    private String name;


    PriorityDemo(long rts, String name) {
        this.rts = rts;
        this.name = name;
    }

    public long getRts() {
        return rts;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) throws Exception {

        ThreadPoolExecutor ruleExecutor = new ThreadPoolExecutor(
                2,             /* minimum (core) thread count */
                2,        /* maximum thread count */
                Long.MAX_VALUE, /* timeout */
                TimeUnit.NANOSECONDS,
                new PriorityBlockingQueue<Runnable>(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        PriorityDemo p1 = new PriorityDemo(1234, "name1-1");

        PriorityDemo p2 = new PriorityDemo(1500, "name4-2");

        PriorityDemo p3 = new PriorityDemo(1590, "name5-3");
        PriorityDemo p4 = new PriorityDemo(1490, "name3-4");
        PriorityDemo p5 = new PriorityDemo(1290, "name2-5");

        ruleExecutor.execute(p4);
        ruleExecutor.submit(p5);
        ruleExecutor.execute(p1);
        ruleExecutor.execute(p2);
        ruleExecutor.execute(p3);
        ruleExecutor.execute(p5);
        log.info("submit 3 Runnable");
        Thread.sleep(60*1000);
        ruleExecutor.shutdown();
        log.info("end");

    }

    public int compareTo(PriorityDemo o) {
        // 时间越小越优先
        log.info("this.name={}, o.name={}", this.getName(), o.getName());
        if (this.getRts() < o.rts) {
            return -1;
        }else if(this.getRts()> o.rts){
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void run() {
        Random random = new Random();
        log.info("rts={}, name={}", rts, name);
        try {
            int sleepRandom = random.nextInt(200);
            Thread.sleep(sleepRandom);
        } catch (Exception ex) {
            log.info("sleep exception", ex);
        }
    }
}
