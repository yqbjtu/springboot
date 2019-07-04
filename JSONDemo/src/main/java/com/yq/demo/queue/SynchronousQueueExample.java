package com.yq.demo.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Simple to Introduction
 * className: SynchronousQueueExample
 *
 */
@Slf4j
public class SynchronousQueueExample {

    static class SynchronousQueueProducer implements Runnable {

        protected BlockingQueue<String> blockingQueue;
        final Random random = new Random();

        public SynchronousQueueProducer(BlockingQueue<String> queue) {
            this.blockingQueue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String data = UUID.randomUUID().toString();
                    log.info("Put: {} starts", data);
                    blockingQueue.put(data);
                    log.info("Put: {} done", data);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    static class SynchronousQueueConsumer implements Runnable {

        protected BlockingQueue<String> blockingQueue;

        public SynchronousQueueConsumer(BlockingQueue<String> queue) {
            this.blockingQueue = queue;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while (true) {
                try {
                    log.info(threadName + " taking starts");
                    String data = blockingQueue.take();
                    log.info(threadName
                            + " take(): " + data + " done");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        final BlockingQueue<String> synchronousQueue = new SynchronousQueue<String>();
//        System.out.println(synchronousQueue.offer("1"));
//        System.out.println(synchronousQueue.offer("2"));
//        System.out.println(synchronousQueue.offer("3"));
//        System.out.println(synchronousQueue.poll());
//        System.out.println(synchronousQueue.size());

        SynchronousQueueProducer queueProducer = new SynchronousQueueProducer(
                synchronousQueue);
        new Thread(queueProducer).start();

        SynchronousQueueConsumer queueConsumer1 = new SynchronousQueueConsumer(
                synchronousQueue);
        new Thread(queueConsumer1).start();

        SynchronousQueueConsumer queueConsumer2 = new SynchronousQueueConsumer(
                synchronousQueue);
        new Thread(queueConsumer2).start();

    }
}
