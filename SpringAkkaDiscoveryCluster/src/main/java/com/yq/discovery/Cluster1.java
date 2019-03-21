package com.yq.discovery;

import akka.actor.ActorSystem;
import akka.management.cluster.bootstrap.ClusterBootstrap;
import akka.management.javadsl.AkkaManagement;

/**
 * Simple to Introduction
 * className: Cluster1
 *
 * @author EricYang
 * @version 2019/3/20 14:01
 */
public class Cluster1 {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("test");
        ClusterBootstrap clusterBootstrap = ClusterBootstrap.get(system);

        // Akka Management hosts the HTTP routes used by bootstrap
        AkkaManagement.get(system).start();

        // Starting the bootstrap process needs to be done explicitly
        ClusterBootstrap.get(system).start();
    }

}
