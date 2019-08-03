package com.yq.myvolatile;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by EricYang on 2019/7/13.
 */
@Slf4j
public class VolatileDemo {
    private volatile int count = 0;

    public static void main(String[] args) {
        VolatileDemo demo = new VolatileDemo();
        System.out.println("initial value");
        demo.showCountValue();
        long begin = System.currentTimeMillis();

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            MyCounterTask task = new MyCounterTask(demo, "Task-" + i);
            executor.execute(task);
        }
        System.out.println("submit to threadPool done ");
        executor.shutdown();
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("final value after executed by multi-thread.");
        demo.showCountValue();
    }

    public  void add() {
        count = count + 1;
    }

    public int getCount() {
        return count;
    }

    private void showCountValue() {
        System.out.println("count=" + count);
    }
}
