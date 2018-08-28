package com.yq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;


@SpringBootApplication
@EnableWebFlux
public class WebFluxHWDemoApplication {
    private static final Logger log = LoggerFactory.getLogger(WebFluxHWDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebFluxHWDemoApplication.class, args);
    }


}
