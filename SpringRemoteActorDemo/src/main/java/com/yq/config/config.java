package com.yq.config;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Simple to Introduction
 * className: config
 *
 * @author EricYang
 * @version 2019/2/26 11:28
 */

@Configuration
@Slf4j
public class config {

    private static final String ACTOR_SYSTEM_NAME = "RemoteActorDemo";
    private static final String AKKA_CONF_FILE_NAME = "actor-system.conf";

    @Bean
    public ActorSystem actorSystem() {
        log.info("create a bean for actorSystem, myClusterConfig={}");

        Config config = ConfigFactory.parseResources(AKKA_CONF_FILE_NAME)
                .withFallback(ConfigFactory.load());

        // Create an Akka system
        ActorSystem system = ActorSystem.create(ACTOR_SYSTEM_NAME, config);

        return system;
    }
}
