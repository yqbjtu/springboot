package com.yq.lock;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Simple to Introduction
 * className: JStackCase
 *
 * @author EricYang
 * @version 2019/9/16 9:26
 */
public class JStackCase {
    public static Executor executor = Executors.newFixedThreadPool(5);
    public static Object lockObj  = new Object();

    public static void main(String[] args) {
        Task task1 = new Task();
        Task task2 = new Task();
        executor.execute(task1);
        executor.execute(task2);
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            synchronized(lockObj) {
                calculate();
            }
        }

        private void calculate() {
            int i = 0;
            while(true) {
                i++;
            }
        }
    }
}
