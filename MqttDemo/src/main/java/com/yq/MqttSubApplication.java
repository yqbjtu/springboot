package com.yq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Simple to Introduction
 * className: MqttSubApplication
 *
 * @author EricYang
 * @version 2019/2/21 9:56
 */


@SpringBootApplication
@Slf4j
public class MqttSubApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqttSubApplication.class, args);
        log.info("MqttSubApplication start done.");
    }


}