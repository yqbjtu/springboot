package com.yq.map;

import com.google.common.base.Function;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.MapMaker;
import org.apache.commons.collections4.map.PassiveExpiringMap;

import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * Simple to Introduction
 * className: LRUCache
 *
 * @author EricYang
 * @version 2019/8/13 9:04
 */
public class LRUExpiredCache extends LinkedHashMap {
    private static final long serialVersionUID = 1L;
    protected int maxElements;

    public LRUExpiredCache(int maxSize) {
        super(maxSize, 0.75F, true);
        maxElements = maxSize;
    }

    protected boolean removeEldestEntry(java.util.Map.Entry eldest) {
        return size() > maxElements;
    }


    public static void main(String[] args) {
        String str1 = "str1";
        String str2 = "str2";
        String str3 = "str3";
        String str4 = "str4";

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
