package com.yq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages = {"com.yq"})

public class GetPortDemoApp {
    private static final Logger log = LoggerFactory.getLogger(GetPortDemoApp.class);

    public static void main(String[] args) {
        SpringApplication.run(GetPortDemoApp.class, args);
    }

}
