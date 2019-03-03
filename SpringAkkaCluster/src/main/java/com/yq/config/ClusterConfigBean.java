package com.yq.config;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
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
    private static final String ACTOR_SYSTEM_NAME = "ClusterDemo";

    @Autowired
    SpringClusterConfig myClusterConfig;

    @Bean
    public SpringClusterConfig springClusterConfig() {
        log.info("Create a springClusterConfig bean");
        return new SpringClusterConfig();
    }

    @Bean
    public ActorSystem actorSystem() {
        log.info("create a bean for actorSystem, myClusterConfig={}", myClusterConfig);
        String port = myClusterConfig.getPort();

        Config config = ConfigFactory.parseString(
                "akka.remote.artery.canonical.port=" + port)
                .withFallback(ConfigFactory.parseString("akka.cluster.roles=[rule1]"))
                .withFallback(ConfigFactory.load());

        // Create an Akka system
        ActorSystem system = ActorSystem.create(ACTOR_SYSTEM_NAME, config);

        return system;
    }
}
