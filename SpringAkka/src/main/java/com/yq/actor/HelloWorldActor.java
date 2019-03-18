package com.yq.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import javax.inject.Named;

import com.yq.domain.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private List<String> stringList = new ArrayList<>();

    private final static String LOCAL_FULL_RESULT_ACTOR_PATH = "akka://Hello/user/resultActor";

    private volatile boolean hasScheduler = false;

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
                .matchAny(msg -> {
                    log.info("received unknown message={}. hasScheduler={} ", msg, hasScheduler);
                    String msgReceived = (String)msg;
                    stringList.add(msgReceived);

                    //累计消息然后发送
                    if (!hasScheduler) {
                        ActorSystem actorSystem = getContext().getSystem();

                        actorSystem.scheduler().scheduleOnce(Duration.ofMillis(1000 * 5), new Runnable() {
                            @Override
                            public void run() {
                                Result result = new Result();
                                result.setCode(001);
                                result.setContent(String.join(",",  stringList.toArray(new String[stringList.size()])));

                                ActorSelection as = actorSystem.actorSelection(LOCAL_FULL_RESULT_ACTOR_PATH);
                                as.tell(result, ActorRef.noSender());
                                stringList = new ArrayList<>();
                                hasScheduler = false;
                            }
                        },actorSystem.dispatcher());
                    }
                })
                .build();
    }


}

