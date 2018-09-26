package com.yq;

import com.yq.config.ConsulConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication(scanBasePackages = {"com.yq"})
@EnableDiscoveryClient
@Configuration
public class ConsulClientDemoApp {
    private static final Logger log = LoggerFactory.getLogger(ConsulClientDemoApp.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ConsulClientDemoApp.class, args);
        ConsulConfig consulConfig = (ConsulConfig) context.getBean("consulConfig");
        log.info("consulConfig={}", consulConfig);
    }

}
