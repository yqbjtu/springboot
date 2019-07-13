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
    private int interval = 1;

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

    static public Props props(int interval) {
        return Props.create(MyTimerActor.class, () -> new MyTimerActor(interval));
    }

    public MyTimerActor(int interval) {
        //已生成对象就发一个次性的timer时间
        this.interval = interval;
        getTimers().startSingleTimer(TICK_KEY, new FirstTick(), Duration.ofMillis(5000));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(
                        FirstTick.class,
                        message -> {
                            log.info("receive FirstTick event");
                            //收到一次性的timer时间后，产生一个周期性的事件
                            getTimers().startPeriodicTimer(TICK_KEY, new Tick("test1"), Duration.ofSeconds(interval));
                        })
                .match(
                        Tick.class,
                        message -> {
                            log.info("receive Tick event {}", message.getMsg());
                        })
                .build();
    }
}
