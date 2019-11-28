package com.yq.lock.condition;

/**
 * Simple to Introduction
 * className: MyServiceThread1
 *
 * @author EricYang
 * @version 2019/11/22 16:28
 */
public class MyServiceThread1 implements Runnable{

    private ConditionDemo service;

    public MyServiceThread1(ConditionDemo service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.awaitA();
    }
}
