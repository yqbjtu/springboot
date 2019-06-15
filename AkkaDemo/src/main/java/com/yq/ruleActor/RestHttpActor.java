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
import com.yq.rule.node.external.RestHttpRuleNode;
import com.yq.rule.node.filter.FilterScriptRuleNode;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//import org.codehaus.jackson.map.ObjectMapper;

public class RestHttpActor extends AbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    static public Props props(IoTContext context) {
        return Props.create(RestHttpActor.class, () -> new RestHttpActor(context));
    }


    private final IoTContext context;

    private String greeting = "";

    public RestHttpActor(IoTContext context) {
        this.context = context;
    }

    /*
    The former will update the greeting state of the Actor and
    the latter will trigger a sending of the greeting to the Printer Actor.
     */
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(FilterScriptActor.DeviceDataEvent.class, deviceDataEvent -> {
                    long threadId = Thread.currentThread().getId();
                    this.greeting = deviceDataEvent.deviceName;
                    log.info("match DeviceDataEvent. receiveBuilder greeting={}, threadId={}", greeting, threadId);

                    //遍历设备的所规则，然后逐条进行运行， 获取规则内容，进行js运算，如果是true就报警，否则就停止
                    RuleChain ruleChain = deviceDataEvent.ruleChain;

                    //遍历ruleChain找到currentRuleNodeId对应的rule
                    List<BaseRule> ruleList = ruleChain.getRuleList();
                    Map<String, BaseRule> nodeIdRuleNodeMap = ruleChain.getNodeIdRuleNodeMap();
                    Iterator<BaseRule> itr = ruleList.iterator();
                    String currentRuleNodeId = deviceDataEvent.currentRuleNodeId;
                    RestHttpRuleNode restRuleNode = (RestHttpRuleNode)nodeIdRuleNodeMap.get(currentRuleNodeId);
                    //rest 是最终的处理，因此不需要关注tellNext了
                    restRuleNode.process();
                })
                .build();
    }

}

