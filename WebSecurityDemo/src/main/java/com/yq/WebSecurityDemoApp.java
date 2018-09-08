package com.yq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages = {"com.yq"})
//@EnableAutoConfiguration(exclude = {
//        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
//})
public class WebSecurityDemoApp {
    private static final Logger log = LoggerFactory.getLogger(WebSecurityDemoApp.class);

    public static void main(String[] args) {
        SpringApplication.run(WebSecurityDemoApp.class, args);
    }

}
