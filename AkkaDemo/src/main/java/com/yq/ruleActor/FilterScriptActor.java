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
import com.yq.rule.node.filter.FilterScriptRuleNode;
import com.yq.rule.node.action.SendMailRule;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//import org.codehaus.jackson.map.ObjectMapper;

public class FilterScriptActor extends AbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    static public Props props(IoTContext context) {
        return Props.create(FilterScriptActor.class, () -> new FilterScriptActor(context));
    }

    static public class DeviceDataEvent {
        public final String deviceName;
        public final String deviceId;
        public final String currentRuleNodeId;
        public final RuleChain ruleChain;

        public  Map<String, Object> sensorDataMap;

        public DeviceDataEvent(String deviceName, Map<String, Object> sensorDataMap, String currentRuleNodeId, RuleChain ruleChain) {
            this.deviceName = deviceName;
            this.sensorDataMap = sensorDataMap;
            this.currentRuleNodeId = currentRuleNodeId;
            this.ruleChain = ruleChain;
            deviceId = (String)sensorDataMap.get("deviceId");
        }
    }

    private final IoTContext context;

    private String greeting = "";

    public FilterScriptActor(IoTContext context) {
        this.context = context;
    }

    /*
    The former will update the greeting state of the Actor and
    the latter will trigger a sending of the greeting to the Printer Actor.
     */
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(DeviceDataEvent.class, deviceDataEvent -> {
                    long threadId = Thread.currentThread().getId();
                    this.greeting = deviceDataEvent.deviceName;
                    log.info("match DeviceDataEvent. receiveBuilder greeting={}, threadId={}", greeting, threadId);

                    //遍历设备的所规则，然后逐条进行运行， 获取规则内容，进行js运算，如果是true就报警，否则就停止


                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonStr = objectMapper.writeValueAsString(deviceDataEvent.sensorDataMap);

                    JSONObject json = JSON.parseObject(jsonStr);

                    RuleChain ruleChain = deviceDataEvent.ruleChain;

                    //遍历ruleChain找到currentRuleNodeId对应的rule

                    List<BaseRule> ruleList = ruleChain.getRuleList();
                    Map<String, BaseRule> nodeIdRuleNodeMap = ruleChain.getNodeIdRuleNodeMap();
                    Iterator<BaseRule> itr = ruleList.iterator();
                    String currentRuleNodeId = deviceDataEvent.currentRuleNodeId;
                    FilterScriptRuleNode filterRule = null;

                    while(itr.hasNext()) {
                        BaseRule baseRule = itr.next();
                        if (Objects.equals(currentRuleNodeId, baseRule.getId())) {
                            String ruleNodeType = baseRule.getType();
                            String ruleNodeActualClass = baseRule.getNodeActualClass();
                            filterRule = (FilterScriptRuleNode)(baseRule);
                            break;
                        }
                    }

                    String funcContent = filterRule.getContent();
                    boolean result = executeFilterFunction(json, null,"DeviceData", funcContent);
                    if (result) {
                        //查询filterRule自己关联的ruleNode关系

                        List<RuleRelation> relationList = filterRule.getRelationList();
                        Iterator<RuleRelation> itrRelation = relationList.iterator();
                        while(itrRelation.hasNext()) {
                            RuleRelation relation = (RuleRelation) itrRelation.next();
                            //如果true关闭配置成向多个Actor进行输出，就一个一个遍历然后发送定制消息进行输出
                            if (Objects.equals(relation.getFromId(), currentRuleNodeId)) {
                                String toId = relation.getToId();
                                String relationName = relation.getRelationType();
                                if (Objects.equals("true", relationName)) {
                                    //找到toId，然后tell给toId
                                    //从context中找到对应actor，然后调用该actor的tell， 每个RuleId是什么类型已经很清楚了，因此只需要
                                    BaseRule toRuleNode = nodeIdRuleNodeMap.get(toId);
                                    String className = toRuleNode.getClass().getCanonicalName();
                                    sendToNext(className, context, toRuleNode, toId, ruleChain, deviceDataEvent);
                                }

                            }
                        }
                        log.info("true");
                    }
                    else {
                        List<RuleRelation> relationList = filterRule.getRelationList();
                        Iterator<RuleRelation> itrRelation = relationList.iterator();
                        while(itrRelation.hasNext()) {
                            RuleRelation relation = (RuleRelation) itrRelation.next();
                            //如果true关闭配置成向多个Actor进行输出，就一个一个遍历然后发送定制消息进行输出
                            if (Objects.equals(relation.getFromId(), currentRuleNodeId)) {
                                String toId = relation.getToId();
                                String relationName = relation.getRelationType();
                                if (Objects.equals("false", relationName)) {
                                    //找到toId，然后tell给toId
                                    //从context中找到对应actor，然后调用该actor的tell， 每个RuleId是什么类型已经很清楚了，因此只需要
                                    BaseRule toRuleNode = nodeIdRuleNodeMap.get(toId);
                                    String className = toRuleNode.getClass().getCanonicalName();
                                    sendToNext(className, context, toRuleNode, toId, ruleChain, deviceDataEvent);
                                }
                            }
                        }

                        log.info("false");
                    }
                })
                .matchEquals("done", m ->
                        getContext().stop(getSelf())
                )
                .matchAny(msg -> log.info("received unknown message={}", msg))
                .build();
    }

    private void sendToNext(String ruleNodeActualClass, IoTContext context, BaseRule toRuleNode, String toId,
                             RuleChain ruleChain, DeviceDataEvent deviceDataEvent) {
        switch (ruleNodeActualClass) {
            case "com.yq.rule.node.filter.FilterScriptRuleNode":
                log.info("no filter worker now for class={}", ruleNodeActualClass);
                break;
            case "com.yq.rule.node.action.CreateAlarmRule":
                CreateAlarmActionActor.AlarmMessage temp =
                        new CreateAlarmActionActor.AlarmMessage(deviceDataEvent.deviceId,
                                deviceDataEvent.deviceName, (CreateAlarmRule)toRuleNode, toId, ruleChain);
                context.getCreateAlarmActor().tell(temp, getSelf());
                break;
            case "com.yq.rule.node.action.SendMailRule":
                SendMailActionActor.MailMessage tempMail =
                        new SendMailActionActor.MailMessage(deviceDataEvent.deviceId,
                                deviceDataEvent.deviceName, (SendMailRule)toRuleNode);
                context.getSendMailActor().tell(tempMail, getSelf());
                break;

            default:
                log.info("no found root rule Node class={}", ruleNodeActualClass);
        }
    }

    private boolean executeFilterFunction(JSONObject msg, JSONObject metadata, String msgType, String func) {
        boolean result = true;
        try {
            ScriptEngineManager sem = new ScriptEngineManager();
            ScriptEngine engine = sem.getEngineByName("javascript");

            //定义函数
            engine.eval("function filter(msg, metadata, msgType){ " + func + "}");
            // 执行js函数
            Invocable jsInvoke = (Invocable) engine;
            Object obj = jsInvoke.invokeFunction("filter", msg, metadata, msgType);
            //方法的名字，参数
           log.info("jsResult={}", obj);
            result = (Boolean)obj;
        }
        catch(Exception ex) {
            log.info("exception", ex);
            result = false;
        }

        return result;
    }
}

