package com.yq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;

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
        byte[] existingValueByte = {};
        try {
            String existingValueStr = new String(existingValueByte, "UTF-8");
            log.info("existingValueStr={}", existingValueStr);
        }
        catch (Exception ex) {
            log.warn("ex", ex);
        }
    }

}
