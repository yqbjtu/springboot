package com.yq.ruleActor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.yq.rule.node.SendMailRule;

//#printer-messages
public class SendMailActionActor extends AbstractActor {
    //#printer-messages
    static public Props props() {
        return Props.create(SendMailActionActor.class, () -> new SendMailActionActor());
    }

    //#printer-messages
    static public class MailMessage {
        public final String deviceId;
        public final String ruleId;

        public final SendMailRule rule;

        public MailMessage(String deviceId, String ruleId, SendMailRule rule) {
            this.deviceId = deviceId;
            this.ruleId = ruleId;
            this.rule = rule;
        }
    }
    //#printer-messages

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public SendMailActionActor() {
    }

    /*
    The createReceive method should handle the messages the actor expects.
    In the case of Greeter, it expects two types of messages: DeviceDataEvent and Greet.
     */
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(MailMessage.class, greeting -> {
                    long threadId = Thread.currentThread().getId();
                    log.info("deviceId={}, ruleId={}, threadId={}. clear latest alarm", greeting.deviceId ,greeting.ruleId, threadId);
                })
                .build();
    }

}

