package com.yq.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *
 */
@Component
@Data
@Slf4j
@Order(1)
public class ClusterConfig {

    @Value("${zkServers}")
    private String zkServers;

    @Value("${zkNamespace}")
    private String zkNamespace;

    @Value("${cluster.port}")
    private int port;

    @PostConstruct
    public void postConstruct(){
        log.info("zkServers={}, zkNamespace={}, port={}", zkServers, zkNamespace, port);
    }
}
