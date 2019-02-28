package com.yq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Simple to Introduction
 * className: ClusterConfigBean
 *
 * @author EricYang
 * @version 2019/2/27 17:21
 */

@Configuration
@Order(3)
@Slf4j
public class ClusterConfigBean {

    @Bean
    public SpringClusterConfig springClusterConfig() {
        log.info("Create a springClusterConfig");
        return new SpringClusterConfig();
    }
}
