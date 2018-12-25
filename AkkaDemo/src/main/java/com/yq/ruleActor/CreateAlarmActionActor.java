package com.yq.ruleActor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.yq.rule.node.CreateAlarmRule;


public class CreateAlarmActionActor extends AbstractActor {

    static public Props props() {
        return Props.create(CreateAlarmActionActor.class, () -> new CreateAlarmActionActor());
    }

    static public class AlarmMessage {
        public final String deviceId;
        public final String deviceName;

        public final CreateAlarmRule rule;

        public AlarmMessage(String deviceId, String deviceName, CreateAlarmRule rule) {
            this.deviceId = deviceId;
            this.deviceName = deviceName;
            this.rule = rule;
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
                .match(AlarmMessage.class, alarmMessage -> {
                    long threadId = Thread.currentThread().getId();
                    CreateAlarmRule createAlarmRule = alarmMessage.rule;
                    String alarmMsgContent = createAlarmRule.getContent();
                    log.info("alarmMsgContent={}, deviceName={}, threadId={}, create an alarm if not created",
                            alarmMsgContent, alarmMessage.deviceName ,threadId);
                })
                .build();
    }

}

