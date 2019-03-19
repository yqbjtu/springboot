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
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private Environment env;

    @Bean
    public SpringClusterConfig springClusterConfig() {
        log.info("Create a springClusterConfig bean");
        return new SpringClusterConfig();
    }

    @Bean
    public ActorSystem actorSystem() {
        log.info("create a bean for actorSystem, myClusterConfig={}", myClusterConfig);
        String port = myClusterConfig.getPort();

        //canonical.hostname
        //InetAddress.getLocalHost.getHostAddress
        Map<String, Object> configMap = new HashMap<>();
        String host = "localhost";
        try {
            host = InetAddress.getLocalHost().getHostAddress();
            log.info("host={}", host);
        }
        catch (Exception ex) {

        }
        configMap.put("akka.remote.artery.canonical.port", port);
        //String envValue = env.getProperty(ENV_NAME); 如果是用docker swarm可以将每个部署image的机器ip传递过来作为host
        //同时也可以将每个环节中的seedNodes

        configMap.put("akka.remote.artery.canonical.hostname", host);
        String seedNodes = "akka://ClusterDemo@"+ host+ ":3000";
        List<String> list = (new ArrayList<String>(0));
        list.add(seedNodes);
        configMap.put("akka.cluster.seed-nodes", list);

         Config config = ConfigFactory.parseMap(configMap)
                .withFallback(ConfigFactory.parseString("akka.cluster.roles=[rule1]"))
                .withFallback(ConfigFactory.load());

        // Create an Akka system
        ActorSystem system = ActorSystem.create(ACTOR_SYSTEM_NAME, config);

        return system;
    }
}
