package com.yq;

import com.yq.config.ConsulConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.yq"})
public class ConsulClientDemoApp {
    private static final Logger log = LoggerFactory.getLogger(ConsulClientDemoApp.class);

    @Autowired
    private ConsulConfig consulConfig;

    public static void main(String[] args) {
        SpringApplication.run(ConsulClientDemoApp.class, args);
    }

}
