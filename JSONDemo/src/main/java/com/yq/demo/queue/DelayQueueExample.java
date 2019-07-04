package com.yq.demo.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.DelayQueue;

/**
 * Simple to Introduction
 * className: DelayQueueExample
 *
 */
@Slf4j
public class DelayQueueExample {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedElement> queue= new DelayQueue<>();
        DelayedElement element4 = new DelayedElement( "cache 4 seconds",4000);
        DelayedElement element1 = new DelayedElement( "cache 1 seconds",1000);
        log.info("put starts");
        queue.put(element1);
        queue.put(element4);
        log.info("put done and take starts");
        System.out.println( queue.take());
        log.info("take the first element done");


        System.out.println( queue.take());
        log.info("take the second element done");

    }
}