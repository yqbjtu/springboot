package com.yq.config;

import akka.actor.ActorSystem;
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
public class config {

    @Bean
    public ActorSystem actorSystem() {
        ActorSystem system = ActorSystem.create("Hello");
        return system;
    }

}
