
package com.yq.service;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;


/**
 * Simple to Introduction
 * className: RuleValidator
 *
 * @author EricYang
 * @version 2019/6/25 13:39
 */
public interface IoTContextService {

    public boolean init();
    public ActorRef getActor(String actorName);
    public ActorRef getWorkerActor();
    public ActorSystem getActorSystem();
}
