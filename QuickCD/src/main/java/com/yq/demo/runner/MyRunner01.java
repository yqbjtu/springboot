package com.yq.demo.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(value=1)
public class MyRunner01 implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(MyRunner01.class);

    @Override
    public void run(String... arg0) throws Exception {
        String string = String.join(",", arg0);
        log.info("my first runner order is 1. args:" + string);
    }

}
