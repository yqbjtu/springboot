package com.yq.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Simple to Introduction
 * className: DeathLockDemo
 *
 * @author EricYang
 * @version 2019/9/16 9:02
 */
public class DeathLockDemo {
    public static void main(String[] args) {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    lock1.lock();
                    TimeUnit.SECONDS.sleep(1);
                    lock2.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    lock2.lock();
                    TimeUnit.SECONDS.sleep(1);
                    lock1.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.setName("mythread1");
        t2.setName("mythread2");
        t1.start();
        t2.start();
    }
}
