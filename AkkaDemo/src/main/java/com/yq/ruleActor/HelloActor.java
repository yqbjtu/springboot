package com.yq.ruleActor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yq.context.IoTContext;
import com.yq.rule.BaseRule;
import com.yq.rule.RuleChain;
import com.yq.rule.RuleRelation;
import com.yq.rule.node.action.CreateAlarmRule;
import com.yq.rule.node.action.SendMailRule;
import com.yq.rule.node.filter.FilterScriptRuleNode;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class HelloActor extends AbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    static public Props props(IoTContext context) {
        return Props.create(HelloActor.class, () -> new HelloActor(context));
    }

    private final IoTContext context;

    private String greeting = "";

    public HelloActor(IoTContext context) {
        this.context = context;
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
                    log.info("match exception. receiveBuilder, threadId={}", msg, threadId);
                    throw new Exception("actor process message exception");

                })
                .matchEquals("stop", m ->
                        getContext().stop(getSelf())
                )
                .matchAny(msg -> log.info("received unknown message={}", msg))
                .build();
    }


}

