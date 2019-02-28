
package com.yq.service;


import akka.actor.ActorRef;


/**
 * Simple to Introduction
 * className: RuleValidator
 *
 * @author EricYang
 * @version 2019/6/25 13:39
 */
public interface MyContextService {

    public void init();
    public ActorRef getActor(String actorName);
    public ActorRef getWorkerActor();

}
