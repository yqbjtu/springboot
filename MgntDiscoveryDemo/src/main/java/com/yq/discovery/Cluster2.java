package com.yq.discovery;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;
import akka.cluster.ClusterReadView;
import akka.http.impl.engine.client.PoolConductor;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.management.cluster.bootstrap.ClusterBootstrap;
import akka.management.javadsl.AkkaManagement;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.yq.constant.ClusterConstants;
import lombok.extern.slf4j.Slf4j;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.server.AllDirectives;
import akka.management.cluster.bootstrap.ClusterBootstrap;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple to Introduction
 * className: Cluster1
 *
 * @author EricYang
 * @version 2019/3/20 14:01
 */
@Slf4j
public class Cluster2 {
    private static final String ACTOR_SYSTEM_NAME = "ClusterDemo";
    private static final String AKKA_CONF_FILE_NAME = "akka-system11112";
    private static final String MGNT_HOSTNAME = "akka.management.http.hostname";
    private static final String MGNT_PORT = "akka.management.http.port";
    public static void main(String[] args) {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ClusterConstants.NETTY_PORT, 3001);

        Config config = ConfigFactory.parseResources(AKKA_CONF_FILE_NAME)
                .withFallback(ConfigFactory.parseMap(configMap))
                .withFallback(ConfigFactory.parseString("akka.cluster.roles=[rule1]"))
                .withFallback(ConfigFactory.parseString(MGNT_HOSTNAME+"=\"127.0.0.1\""))
                .withFallback(ConfigFactory.parseString(ClusterConstants.NETTY_HOSTNAME+"=\"127.0.0.1\""))

                .withFallback(ConfigFactory.load());

        // Create an Akka system
        ActorSystem system = ActorSystem.create(ACTOR_SYSTEM_NAME, config);

        // Akka Management hosts the HTTP routes used by bootstrap
        AkkaManagement.get(system).start();
        log.info("akka mgnt start");

        // Starting the bootstrap process needs to be done explicitly
        ClusterBootstrap.get(system).start();
        log.info("clusterBootstrap mgnt start");

        Cluster cluster = Cluster.get(system);
        ClusterReadView readView = cluster.readView();

        Materializer mat = ActorMaterializer.create(system);

        system.log().info("Started [" + system + "], cluster.selfAddress = " + cluster.selfAddress() + ")");

        //#start-akka-management
        AkkaManagement.get(system).start();
        //#start-akka-management
        ClusterBootstrap.get(system).start();

        //Http.get(system).bindAndHandle(complete("Hello world").flow(system, mat), ConnectHttp.toHost("0.0.0.0", 8080), mat);
        log.info("cluster mgnt start selfAddress={}", cluster.selfAddress());

    }

}
