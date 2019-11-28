package com.yq.lock.condition;

/**
 * Simple to Introduction
 * className: ConditionAppMain
 *
 * @author EricYang
 * @version 2019/11/22 16:29
 */
public class ConditionAppMain {
    public static void main(String[] args) throws InterruptedException {
        ConditionDemo service = new ConditionDemo();
        Runnable runnable1 = new MyServiceThread1(service);
        Runnable runnable2 = new MyServiceThread2(service);

        new Thread(runnable1, "a").start();
        new Thread(runnable2, "b").start();

        // 线程sleep2秒钟
        Thread.sleep(2000);
        // 唤醒所有持有conditionA的线程
        service.signalAllA();

        Thread.sleep(2000);
        // 唤醒所有持有conditionB的线程
        service.signalAllB();
    }
}
