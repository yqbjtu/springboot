package com.yq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple to Introduction
 * className: MainDemo
 *
 * @author EricYang
 * @version 2018/11/14 9:58
 */
@Slf4j
public class MainDemo {

    public static void main(String[] args) {
//        byte[] existingValueByte = {};
//        try {
//            String existingValueStr = new String(existingValueByte, "UTF-8");
//            log.info("existingValueStr={}", existingValueStr);
//        }
//        catch (Exception ex) {
//            log.warn("ex", ex);
//        }

//        String topic = "a_6pmkr/6pn5m/p_6pn5o";
//        Map<String,String> connectorIdAppIdMap = new HashMap<>();
//        if (topic != null && topic.length() > 13) {
//            String appId = topic.substring(2,7);
//            String connectorId = topic.substring(8,13);
//            connectorIdAppIdMap.put(connectorId,appId);
//        }
//        else {
//            log.warn("topic={} is null or length is LT 13.", topic);
//        }


        String topic1 = "a_6pmkr/6pn5m/p_6pn5o";
        String topic2 = "a_6pmkr/4pn5m/p_6pn5o";
        List<String> list = new ArrayList<>();
        list.add(topic1);
        list.add(topic2);
        int index = list.indexOf(topic1);

        log.info("index={}", index);
    }

}
