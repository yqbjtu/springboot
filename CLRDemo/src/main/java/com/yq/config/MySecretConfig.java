package com.yq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Simple to Introduction
 * className: MySecretConfig
 *
 * @author EricYang
 * @version 2018/11/11 12:45
 */

@Configuration
@Slf4j
public class MySecretConfig {
    @Value("${mySecretKey:defatulValue}")
    private  String mySecretKey;

    @Bean
    public String getMySecretKeyFromCfg() {
        return mySecretKey;
    }
}