package com.yq.ruleActor;

import akka.actor.AbstractActorWithTimers;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.yq.context.IoTContext;

import java.time.Duration;

/**
 * Simple to Introduction
 * className: MyTimerActor
 *
 * @author EricYang
 * @version 2019/7/4 10:20
 */
public class MyTimerActor extends AbstractActorWithTimers {
    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private static Object TICK_KEY = "TickKey";

    private static final class FirstTick {}

    private static final class Tick {
        private String msg;
        public Tick(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }

    static public Props props() {
        return Props.create(MyTimerActor.class, () -> new MyTimerActor());
    }

    public MyTimerActor() {
        getTimers().startSingleTimer(TICK_KEY, new FirstTick(), Duration.ofMillis(5000));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(
                        FirstTick.class,
                        message -> {
                            log.info("receive FirstTick event");
                            getTimers().startPeriodicTimer(TICK_KEY, new Tick("test1"), Duration.ofSeconds(1));
                        })
                .match(
                        Tick.class,
                        message -> {
                            log.info("receive Tick event {}", message.getMsg());
                        })
                .build();
    }
}
