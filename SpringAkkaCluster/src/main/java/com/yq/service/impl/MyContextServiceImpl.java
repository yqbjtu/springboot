package com.yq.service.impl;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.yq.actor.ClusterWorkerActor;
import com.yq.actor.WorkActor;
import com.yq.config.SpringClusterConfig;
import com.yq.service.MyContextService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
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
@Lazy
@Order(4)
public class MyContextServiceImpl implements MyContextService {

    @Autowired
    private ActorSystem actorSystem;

    Map<String, ActorRef> classActorRefMap = new HashMap<>();


    boolean isInitialized = false;

    MyContextServiceImpl() {
        //初始化
        boolean initResultInConstructor = init();
        log.info("initResultInConstructor={}, actorSystem={}", initResultInConstructor, actorSystem);
    }

    @Override
    public boolean init() {
        synchronized (this) {
            if (!isInitialized && actorSystem != null) {
                //初始化
                try {
                    /*String port = myClusterConfig.getPort();

                    Config config = ConfigFactory.parseString(
                            "akka.remote.artery.canonical.port=" + port)
                            .withFallback(ConfigFactory.parseString("akka.cluster.roles = [backend]"))
                            .withFallback(ConfigFactory.load());

                    // Create an Akka system
                    //Config config = ConfigFactory.parseResources(AKKA_CONF_FILE_NAME).withFallback(ConfigFactory.load());
                    ActorSystem system = ActorSystem.create(ACTOR_SYSTEM_NAME, config);*/

                    // Create an actor that handles cluster domain events
                    ActorRef clusterActorRef = actorSystem.actorOf(Props.create(ClusterWorkerActor.class),
                            "clusterActor");
                    ActorRef workActorRef = actorSystem.actorOf(Props.create(WorkActor.class),
                            "workerActor");

                    //clusterActorRef.tell("foo1 " + clusterActorRef.toString() + " p_"+ port,ActorRef.noSender());

                    classActorRefMap.put(ClusterWorkerActor.class.getCanonicalName(), clusterActorRef);
                    classActorRefMap.put(WorkActor.class.getCanonicalName(), workActorRef);
                    isInitialized = true;
                }
                catch (Exception ex) {
                    log.error("failed to initialize actors", ex);
                }
            }
        }

        return isInitialized;
    }

    @Override
    public ActorRef getActor(String actorName) {
        ActorRef actor = classActorRefMap.get(actorName);
        return actor;
    }

    @Override
    public ActorRef getWorkerActor() {
        ActorRef actor = classActorRefMap.get(ClusterWorkerActor.class.getCanonicalName());
        return actor;
    }


}
