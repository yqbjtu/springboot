package com.yq.ruleActor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.dispatch.Futures;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.yq.context.IoTContext;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;


public class HelloFutureActor extends AbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    ExecutionContext ec = getContext().getSystem().dispatchers().lookup("my-blocking-dispatcher");

    static public Props props() {
        return Props.create(HelloFutureActor.class, () -> new HelloFutureActor());
    }


    public HelloFutureActor() {

    }

    /*
    The former will update the greeting state of the Actor and
    the latter will trigger a sending of the greeting to the Printer Actor.
     */
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Integer.class, n -> {
                    long threadId = Thread.currentThread().getId();
                    log.info("match blockOp. receiveBuilder, threadId={}", threadId);
                    Future<Integer> f = Futures.future(() -> {
                        Thread.sleep(5000);
                        long tId = Thread.currentThread().getId();
                       log.info("Blocking future finished {}, threadId={}", n, tId);
                        return n;
                    }, ec);

                })
                .matchEquals("stop", m ->
                        getContext().stop(getSelf())
                )
                .matchAny(msg -> {
                    long threadId = Thread.currentThread().getId();
                    log.info("received unknown message={}, threadId={}", msg, threadId);
                })
                .build();
    }


}

