package com.yq.ruleActor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;


public class CreateAlarmActionActor extends AbstractActor {

    static public Props props() {
        return Props.create(CreateAlarmActionActor.class, () -> new CreateAlarmActionActor());
    }

    static public class AlarmMessage {
        public final String deviceId;
        public final String deviceName;

        public AlarmMessage(String deviceId, String deviceName) {
            this.deviceId = deviceId;
            this.deviceName = deviceName;
        }
    }


    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public CreateAlarmActionActor() {
    }

    /*
    The createReceive method should handle the messages the actor expects.
    In the case of Greeter, it expects two types of messages: DeviceDataEvent and Greet.
     */
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(AlarmMessage.class, greeting -> {
                    long threadId = Thread.currentThread().getId();
                    log.info("deviceName={}, threadId={}, create an alarm if not created", greeting.deviceName ,threadId);
                })
                .build();
    }

}

