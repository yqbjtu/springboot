package com.yq.myvolatile;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
@Getter
class MyCounterTask implements Runnable {
    private VolatileDemo demo;
    private String name;

    public MyCounterTask(VolatileDemo demo, String name) {
        this.demo = demo;
        this.name = name;
    }

    @Override
    public void run() {
        long threadId = Thread.currentThread().getId();
        for (int i = 0; i < 10000; i++) {
            demo.add();
        }
        log.info("done a task. count={}, threadId={}, name={}" , demo.getCount(), threadId, name);
    }
}
