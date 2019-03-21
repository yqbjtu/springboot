package com.yq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Simple to Introduction
 * className: LogbackApplication
 *
 * @author EricYang
 * @version 2018/12/16 11:14
 */
@SpringBootApplication
@Slf4j
public class SpringAkkaDiscoveryApplication {
    public static void main(String[] args) {
        long threadId = Thread.currentThread().getId();
        log.info("SpringAkkaDiscoveryApplication Start ... threadId={}", threadId);
        SpringApplication.run(SpringAkkaDiscoveryApplication.class, args);
        log.info("SpringAkkaDiscoveryApplication Start done. threadId={}", threadId);
    }
}
