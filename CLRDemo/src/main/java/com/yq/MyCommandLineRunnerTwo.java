package com.yq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Simple to Introduction
 * className: MyCommandLineRunner
 *
 * @author EricYang
 * @version 2018/11/10 11:15
 */

@Slf4j
@Component
public class MyCommandLineRunnerTwo implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("info myCLR two");
    }
}
