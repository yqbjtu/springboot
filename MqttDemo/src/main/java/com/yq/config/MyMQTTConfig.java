package com.yq.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Simple to Introduction
 * className: MyMQTTConfig
 *
 * @author EricYang
 * @version 2019/1/26 20:31
 */
@Configuration
@Data
public class MyMQTTConfig {
    @Value("${local.mqtt.broker.host}")
    private String localServers;

    @Value("${dev.mqtt.broker.host}")
    private String devServers;

    @Value("${test.mqtt.broker.host}")
    private String testServers;

    @Value("${beta.mqtt.broker.host}")
    private String betaServers;

    @Value("${prod.mqtt.broker.host}")
    private String prodServers;

    @Value("${mqtt.broker.username}")
    private String username;

    @Value("${mqtt.broker.password}")
    private String password;

    @Value("${mqtt.broker.connectTimeout}")
    private int connectTimeout;
    @Value("${mqtt.broker.keepAliveInterval}")
    private int keepAliveInterval;

}
