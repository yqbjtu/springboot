package com.yq.controller;

import lombok.extern.slf4j.Slf4j;

/**
 * Simple to Introduction
 * className: DemoMain
 *
 * @author EricYang
 * @version 2018/10/25 18:33
 */
@Slf4j
public class DemoMain {

    private final static String TOPIC_SHADOW = "/topic/shadow/";


    public static void main(String[] args) {
        String topicDest = "/topic/shadow/a1b2c3";
        String dest = topicDest.substring(TOPIC_SHADOW.length());
        log.info("dest={}", dest);
    }
}
