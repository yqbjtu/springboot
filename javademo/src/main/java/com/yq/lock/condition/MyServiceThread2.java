package com.yq.lock.condition;

/**
 * Simple to Introduction
 * className: MyServiceThread1
 *
 * @author EricYang
 * @version 2019/11/22 16:28
 */
public class MyServiceThread2 implements Runnable{

    private ConditionDemo service;

    public MyServiceThread2(ConditionDemo service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.awaitB();
    }
}
