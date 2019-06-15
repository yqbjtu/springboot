package com.yq.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Simple to Introduction
 * className: SpringClusterConfig
 *
 * @author EricYang
 * @version 2019/2/27 16:25
 */

@Component
@Data
@Order(1)
public class SpringClusterConfig {
    @Value("${cluster.port}")
    private String port;

}
