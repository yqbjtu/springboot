package com.yq.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Simple to Introduction
 * className: HelloWorldListener
 *
 * @author EricYang
 * @version 2019/4/20 10:57
 */
@Component
@Slf4j
public class HelloWorldListener  implements ApplicationListener<HelloWorldEvent>{
    @Override
    @Async
    public void onApplicationEvent(HelloWorldEvent event) {
        HelloWorldEvent myEvent = (HelloWorldEvent)event;
        myEvent.print();
        log.info("event myEvent={}, threadId={}", myEvent, Thread.currentThread().getId());
        try {
            Thread.sleep(1000 * 60);
        } catch (Exception ex) {
            log.warn("event threadId={}", Thread.currentThread().getId(), ex);
        }
    }
}
