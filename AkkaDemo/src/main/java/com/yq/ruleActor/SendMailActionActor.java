package com.yq.ruleActor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.yq.context.IoTContext;
import com.yq.rule.node.action.SendMailRule;

//#printer-messages
public class SendMailActionActor extends AbstractActor {
    //#printer-messages
    static public Props props(IoTContext context) {
        return Props.create(SendMailActionActor.class, () -> new SendMailActionActor(context));
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
    private final IoTContext context;

    public SendMailActionActor(IoTContext context) {
        this.context = context;
    }

    /*
    The createReceive method should handle the messages the actor expects.
    In the case of Greeter, it expects two types of messages: DeviceDataEvent and Greet.
     */
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(MailMessage.class, mailMessage -> {
                    long threadId = Thread.currentThread().getId();
                    SendMailRule mailMessageRule = mailMessage.rule;
                    String mailContent = mailMessageRule.getContent();
                    log.info("mailContent={}, deviceId={}, ruleId={}, threadId={}. send mail",
                            mailContent, mailMessage.deviceId ,mailMessage.ruleId, threadId);

                })
                .build();
    }

}

