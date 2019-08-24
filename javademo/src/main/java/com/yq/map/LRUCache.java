package com.yq.map;

import com.yq.cglib.SampleClass;

import java.util.LinkedHashMap;

/**
 * Simple to Introduction
 * className: LRUCache
 *
 * @author EricYang
 * @version 2019/8/13 9:04
 */
public class LRUCache extends LinkedHashMap {
    private static final long serialVersionUID = 1L;
    protected int maxElements;

    public LRUCache(int maxSize) {
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

        LRUCache demo =new LRUCache(2);
        demo.put(str1, str1);
        demo.put(str2, str1);
        demo.put(str3, str1);
        demo.put(str4, str1);
        demo.put(str3, str1);
        demo.put(str4, str1);
        demo.put(str1, str1);

    }
}
