package com.yq.map;

import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.map.PassiveExpiringMap;
/**
 * Simple to Introduction
 * className: LRUCache
 *
 * @author EricYang
 * @version 2019/8/13 9:04
 */
public class ExpiredCache extends LinkedHashMap {
    private static final long serialVersionUID = 1L;
    protected int maxElements;

    public ExpiredCache(int maxSize) {
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
        //timeToLiveMillis
        PassiveExpiringMap<String, String> map = new PassiveExpiringMap(200);
        map.put(str1, str1);
        map.put(str2, str2);
        String value = map.get(str1);
        System.out.println("1 value:" + value);
        try {
            TimeUnit.MILLISECONDS.sleep(100);
            value = map.get(str1);
            System.out.println("2 value:" + value);
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        value = map.get(str1);
        System.out.println("3 value:" + value);
        map.put(str3, str3);

    }
}
