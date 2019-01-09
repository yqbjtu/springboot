package com.yq.ruleActor;

import akka.actor.AbstractActorWithTimers;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.yq.context.IoTContext;

import java.time.Duration;

/**
 * Simple to Introduction
 * className: TimerActor
 *
 * @author EricYang
 * @version 2019/1/9 10:59
 */

public class TimerActor extends AbstractActorWithTimers {
    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private static Object TICK_KEY = "TickKey";
    public static final class FirstTick {
    }
    public static final class Tick {
    }

    public TimerActor() {
        getTimers().startSingleTimer(TICK_KEY, new FirstTick(), Duration.ofMillis(500));
    }

    static public Props props() {
        return Props.create(TimerActor.class, () -> new TimerActor());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(FirstTick.class, message -> {
                    // do something useful here
                    log.info("FirstTick");
                    //这里每个10秒给自己发送了Tick消息，然后就出发出自己的match(Tick.class, message 执行
                    getTimers().startPeriodicTimer(TICK_KEY, new Tick(), Duration.ofSeconds(10));
                })
                .match(Tick.class, message -> {
                    // do something useful here
                    log.info("Tick");
                })
                .build();
    }
}