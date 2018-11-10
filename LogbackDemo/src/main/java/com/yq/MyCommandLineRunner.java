package com.yq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

/**
 * Simple to Introduction
 * className: MyCommandLineRunner
 *
 * @author EricYang
 * @version 2018/11/10 11:15
 */

@Slf4j
public class MyCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.error("error myCLR");
        log.warn("warn myCLR");
        log.info("info myCLR");
        log.debug("debug myCLR");
        log.trace("trace myCLR");
    }
}
