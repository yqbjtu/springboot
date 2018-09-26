package com.yq.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Simple to Introduction
 * className: ConsulConfig
 *
 * @author EricYang
 * @version 2018/9/1 10:04
 */
@Configuration
@Data
public class ConsulConfig {
    //the default ip is 127.0.0.1
    @Value("${consul.ip:127.0.0.1}")
    private String consulIP;

    // the default port is 8500
    @Value("${consul.port:8500}")
    private int consulPort;

    public ConsulConfig() {
        System.out.println("ConsulConfig启动初始化。。。");
    }
}