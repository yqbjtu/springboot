package com.yq.service.impl;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.yq.actor.ClusterWorkerActor;
import com.yq.actor.WorkActor;
import com.yq.config.SpringClusterConfig;
import com.yq.service.MyContextService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple to Introduction
 * className: IoTContextServiceImpl
 *
 * @author EricYang
 * @version 2018/12/27 15:16
 */


@Service
@Slf4j
public class MyContextServiceImpl implements MyContextService {
    private static final String AKKA_CONF_FILE_NAME = "actor-system.conf";
    private static final String ACTOR_SYSTEM_NAME = "ClusterDemo";

    Map<String, ActorRef> map = new HashMap<>();

    @Autowired
    SpringClusterConfig myClusterConfig;

    boolean isInitialized = false;

    MyContextServiceImpl() {
        //初始化
        init();
    }

    @Override
    public void init() {
        synchronized (this) {
            if (!isInitialized) {
                //初始化
                try {
                    String port = myClusterConfig.getPort();
                    Config config = ConfigFactory.parseString(
                            "akka.remote.artery.canonical.port=" + port)
                            .withFallback(ConfigFactory.parseString("akka.cluster.roles = [backend]"))
                            .withFallback(ConfigFactory.load());

                    // Create an Akka system
                    //Config config = ConfigFactory.parseResources(AKKA_CONF_FILE_NAME).withFallback(ConfigFactory.load());
                    ActorSystem system = ActorSystem.create(ACTOR_SYSTEM_NAME, config);

                    // Create an actor that handles cluster domain events
                    ActorRef clusterActorRef = system.actorOf(Props.create(ClusterWorkerActor.class),
                            "clusterActor");
                    ActorRef workActorRef = system.actorOf(Props.create(WorkActor.class),
                            "workerActor");

                    //clusterActorRef.tell("foo1 " + clusterActorRef.toString() + " p_"+ port,ActorRef.noSender());

                    map.put(ClusterWorkerActor.class.getCanonicalName(), clusterActorRef);
                    map.put(WorkActor.class.getCanonicalName(), workActorRef);
                    isInitialized = true;
                }
                catch (Exception ex) {
                    log.error("failed to initialize actors", ex);
                }
            }
        }
    }

    @Override
    public ActorRef getActor(String actorName) {
        ActorRef actor = map.get(actorName);
        return actor;
    }

    @Override
    public ActorRef getWorkerActor() {
        ActorRef actor = map.get(ClusterWorkerActor.class.getCanonicalName());
        return actor;
    }


}
