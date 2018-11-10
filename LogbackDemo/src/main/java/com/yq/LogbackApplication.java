package com.yq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Simple to Introduction
 * className: LogbackApplication
 *
 * @author EricYang
 * @version 2018/11/10 11:14
 */
@SpringBootApplication
@Slf4j
public class LogbackApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogbackApplication.class, args);
        log.error("error LogbackApplication Start done.");
        log.warn("warn LogbackApplication Start done.");
        log.info("info LogbackApplication Start done.");
        log.debug("debug LogbackApplication Start done.");
        log.trace("trace LogbackApplication Start done.");
    }
}
