package com.yq.threadpool;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
@Getter
class MyTask implements Runnable {
    private String name;

    public MyTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            long threadId = Thread.currentThread().getId();
            Long duration = (long) (Math.random() * 10);
            log.info("Doing a task during : {}, threadId={}" , name , threadId);
            TimeUnit.SECONDS.sleep(duration);
            log.info("done a task during : {}, threadId={}" , name , threadId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
