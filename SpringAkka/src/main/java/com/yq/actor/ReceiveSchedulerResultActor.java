package com.yq.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.yq.domain.Result;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Simple to Introduction
 * className: HelloWorld
 *
 * @author EricYang
 * @version 2019/2/26 11:02
 */
@Named("ReceiveSchedulerResultActor")
@Scope("prototype")
public class ReceiveSchedulerResultActor extends AbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    static public Props props() {
        return Props.create(ReceiveSchedulerResultActor.class, () -> new ReceiveSchedulerResultActor());
    }


    public ReceiveSchedulerResultActor() {

    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Result.class, result -> {
                    log.info("received result. code={}, content={}", result.getCode(), result.getContent());
                })
                .matchAny(msg -> {
                    log.info("unknown message={}", msg);
                })
                .build();
    }


}

