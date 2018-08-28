package com.yq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Simple to Introduction
 * className: WebFluxApplication
 *
 * @author EricYang
 * @version 2018/8/7 9:50
 */
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class WebFluxApplication {

    public static void main(String[] args) {
        log.info("Start GatewayApplication boot");
        SpringApplication.run(WebFluxApplication.class, args);
        log.info("Done Start GatewayApplication boot");
    }

}
