package com.yq.config;

import com.yq.service.UserServiceA;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Simple to Introduction
 * className: ConfigA
 *
 * @author EricYang
 * @version 2018/10/21 15:39
 */
@Configuration
@Order(3)
@Slf4j
public class ConfigA {

    @Bean
    public UserServiceA userServiceA() {
        log.info("ConfigA 执行");
        return new UserServiceA("order 3, userA");
    }
}

