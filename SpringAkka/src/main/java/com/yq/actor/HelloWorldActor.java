package com.yq.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Simple to Introduction
 * className: HelloWorld
 *
 * @author EricYang
 * @version 2019/2/26 11:02
 */
@Named("HelloWorldActor")
@Scope("prototype")
public class HelloWorldActor extends AbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    static public Props props() {
        return Props.create(HelloWorldActor.class, () -> new HelloWorldActor());
    }


    public HelloWorldActor() {

    }

    /*
    The former will update the greeting state of the Actor and
    the latter will trigger a sending of the greeting to the Printer Actor.
     */
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("exception", msg -> {
                    long threadId = Thread.currentThread().getId();
                    log.info("match exception. receiveBuilder, threadId={}", threadId);
                    throw new Exception("actor process message exception");

                })
                .matchEquals("stop", m ->
                        getContext().stop(getSelf())
                )
                .matchAny(msg -> log.info("received unknown message={}", msg))
                .build();
    }


}

