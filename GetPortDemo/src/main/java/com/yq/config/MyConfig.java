package com.yq.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Simple to Introduction
 * className: MyConfig
 *
 * @author EricYang
 * @version 2018/9/9 22:17
 */
@Data
@Configuration
public class MyConfig {
    @Value("${server.port:8080}")
    private int port;
}



