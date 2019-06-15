package com.yq.ruleActor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.yq.context.IoTContext;

/**
 * Simple to Introduction
 * className: HotSwapActor
 *
 * @author EricYang
 * @version 2019/1/9 11:46
 */
public class HotSwapActor extends AbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private AbstractActor.Receive angry;
    private AbstractActor.Receive happy;

//    static public Props props() {
//        return Props.create(HotSwapActor.class, () -> new HotSwapActor());
//    }

    public HotSwapActor() {
        angry =
                receiveBuilder()
                        .matchEquals("foo", s -> {
                            log.info("angry foo");
                            getSender().tell("I am already angry?", getSelf());
                        })
                        .matchEquals("bar", s -> {
                            log.info("angry bar.become happy");
                            getContext().become(happy);
                        })
                        .build();

        happy = receiveBuilder()
                .matchEquals("bar", s -> {
                    log.info("happy bar");
                    getSender().tell("I am already happy :-)", getSelf());
                })
                .matchEquals("foo", s -> {
                    log.info("happy foo. become angry");
                    getContext().become(angry);
                })
                .build();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("foo", s -> {
                        log.info("createReceive foo, become angry");
                        getContext().become(angry);}
                )
                .matchEquals("bar", s -> {
                        log.info("createReceive bar, become happy");
                        getContext().become(happy);}
                )
                .build();
    }
}
