package com.yq.map;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalListeners;
import com.google.common.cache.RemovalNotification;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Simple to Introduction
 * className: LRUCache
 *
 * @author EricYang
 * @version 2019/8/13 9:04
 */
@Slf4j
public class LRUExpiredCache  {
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        String str1 = "str1";
        String str2 = "str2";
        String str3 = "str3";
        String str4 = "str4";

        RemovalListener<Integer, String> removalListener = new RemovalListener<Integer, String>() {
            public void onRemoval(RemovalNotification<Integer, String> removal) {
                Integer key = removal.getKey();
                String value = removal.getValue();
                log.info("removalCause={}, key={}, value={}, threadId={}",
                        removal.getCause(), key, value, Thread.currentThread().getId());
            }
        };

        //Executor executor = Executors.newFixedThreadPool(5);
        //RemovalListener<Integer, String> asyncRemovalListener = RemovalListeners.asynchronous(removalListener, executor);

        Cache<Integer, String> expCache = CacheBuilder.newBuilder()
                 .maximumSize(3)
                .expireAfterAccess(5, TimeUnit.SECONDS)
                //.removalListener(asyncRemovalListener)
                .removalListener(removalListener)

                .build();

        expCache.put(1, str1);
        expCache.put(2, str2);
        expCache.put(3, str3);
        expCache.put(4, str4);


        String retValue1 = null;
        String retValue2 = null;
        String retValue2after3Seconds = null;
        String retValue2after3Seconds2 = null;
        String retValue3 = null;
        String retValue3after6Seconds = null;
        try {
            retValue1 = expCache.asMap().get(1);
            retValue2 = expCache.asMap().get(2);
            retValue3 = expCache.asMap().get(3);
            System.out.println("1 stats:" + expCache.stats() + ", size:"+ expCache.size());
            log.info("read1");
            System.in.read();
            //TimeUnit.SECONDS.sleep(3);
            retValue2after3Seconds = expCache.asMap().get(2);

            log.info("read2");
            System.in.read();
            //TimeUnit.SECONDS.sleep(3);
            retValue2after3Seconds2 = expCache.asMap().get(2);

            retValue3after6Seconds = expCache.asMap().get(3);

//            retValue = expCache.get(2, new Callable<String>() {
//                @Override
//                public String call() throws Exception {
//                    return null;
//                }
//            });
        } catch (Exception ex) {
            System.out.println("exception:1, cause:" + ex.getMessage());
            //ex.printStackTrace();
        }
        System.out.println("key:1, value:" + retValue1 + ", key:2, value:" + retValue2  + ", key:3, value:" + retValue3 +
                ", key:2 after 3 seconds, value:" + retValue2after3Seconds +
                ", key:2 after 3 seconds2, value:" + retValue2after3Seconds2 +
                ", key:3 after 6 seconds, value:" + retValue3after6Seconds);

        System.out.println("2 stats:" + expCache.stats() + ", size:"+ expCache.size());

        while(true) {
            try {
                TimeUnit.SECONDS.sleep(3);
                log.info("read3");
                System.in.read();
                log.info("sleep again");
            } catch (Exception ex) {
                log.info("exception:1, cause:{}", ex.getMessage());
            }
        }
        //Put all features together!
//        LoadingCache<String, String> graphs = CacheBuilder.newBuilder()
//                .maximumSize(1000)
//                .expireAfterWrite(10, TimeUnit.MINUTES)
//                .removalListener(MY_LISTENER)
//                .build(
//                        new CacheLoader<String, String>() {
//                            public String load(Key key) throws AnyException {
//                                return createExpensiveGraph(key);
//                            }
//                        });

    }
}
