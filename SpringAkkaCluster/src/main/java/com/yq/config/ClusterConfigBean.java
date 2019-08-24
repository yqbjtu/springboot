package com.yq.config;

import akka.actor.ActorSystem;
import akka.cluster.Cluster;
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
        /*configMap.put("akka.remote.artery.canonical.port", port);
        //String envValue = env.getProperty(ENV_NAME); 如果是用docker swarm可以将每个部署image的机器ip传递过来作为host
        //同时也可以将每个环节中的seedNodes
        configMap.put("akka.remote.artery.canonical.hostname", host);
        String seedNodes = "akka://ClusterDemo@"+ host+ ":3000";*/


        configMap.put("akka.remote.netty.tcp.port", port);
        configMap.put("akka.remote.netty.tcp.hostname", host);
        //启动先读取zk 指定path的值，如果该值为空，试图zk获取leader，获取后开始将自己设置为seedNodes（ephemeral）。
        // 没有获取leader时，自己等待一段时间后，读取指定path的值， 否则自己作为自己的集群
        // 第一次，path没有值，任意一个实例启动后获取path的值都是空，然后其中一个实例成功写入了地址ip1。其他实例加入到第一个成功写入seedNodes的集群
        //第二次，当原有的写入seedNodes的实例，自己down机了(因为是ephemeral)，这是其余的任意一台机器获得leader，然后将自己的地址ip2为seedNodes，之后新加入的实例使用ip2作为seedNodes
        //第三次，当所有的实例都down了，path的值也没有了（ephemeral类型），下次启动就如同第一次一样。
        String seedNodes = "akka.tcp://ClusterDemo@"+ host + ":3001";
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
