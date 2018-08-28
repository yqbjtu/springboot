package com.yq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication

public class ConsulClientDemoApp {
    private static final Logger log = LoggerFactory.getLogger(ConsulClientDemoApp.class);

    public static void main(String[] args) {
        SpringApplication.run(ConsulClientDemoApp.class, args);
    }


}
