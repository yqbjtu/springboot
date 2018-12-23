package com.yq.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple to Introduction
 * className: TestMain
 *
 * @author EricYang
 * @version 2018/12/13 14:00
 */
@Slf4j
public class TestMain {
    public static void main(String[] args) {
        ArrayList<Map<String, String>> myList = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("key1","value1");
        Map<String, String> map2 = new HashMap<>();
        map2.put("key2","value2");

        myList.add(map1);
        myList.add(map2);
        String jsonStr = JSON.toJSONString(myList);
        JSONArray jsonObject = JSON.parseObject(jsonStr);
        log.info("jsonStr={}, jsonObject={}", jsonStr, jsonObject);
    }
}
