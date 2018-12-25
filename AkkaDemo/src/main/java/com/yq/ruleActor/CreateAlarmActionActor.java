package com.yq.ruleActor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.yq.context.IoTContext;
import com.yq.rule.BaseRule;
import com.yq.rule.RuleChain;
import com.yq.rule.RuleRelation;
import com.yq.rule.node.action.CreateAlarmRule;
import com.yq.rule.node.action.SendMailRule;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class CreateAlarmActionActor extends AbstractActor {

    static public Props props(IoTContext context) {
        return Props.create(CreateAlarmActionActor.class, () -> new CreateAlarmActionActor(context));
    }

    static public class AlarmMessage {
        public final String deviceId;
        public final String deviceName;
        public final String currentRuleNodeId;
        public final RuleChain ruleChain;
        public final CreateAlarmRule rule;

        public AlarmMessage(String deviceId, String deviceName, CreateAlarmRule rule, String currentRuleNodeId, RuleChain ruleChain) {
            this.deviceId = deviceId;
            this.deviceName = deviceName;
            this.rule = rule;
            this.currentRuleNodeId = currentRuleNodeId;
            this.ruleChain = ruleChain;
        }
    }


    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private final IoTContext context;

    public CreateAlarmActionActor(IoTContext context) {
        this.context = context;
    }


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(AlarmMessage.class, alarmMessage -> {
                    long threadId = Thread.currentThread().getId();
                    CreateAlarmRule createAlarmRule = alarmMessage.rule;
                    String alarmMsgContent = createAlarmRule.getContent();
                    log.info("alarmMsgContent={}, deviceName={}, threadId={}, create an alarm if not created",
                            alarmMsgContent, alarmMessage.deviceName ,threadId);

                    RuleChain ruleChain = alarmMessage.ruleChain;
                    Map<String, BaseRule> nodeIdRuleNodeMap = ruleChain.getNodeIdRuleNodeMap();
                    List<RuleRelation> relationList = createAlarmRule.getRelationList();
                    Iterator<RuleRelation> itrRelation = relationList.iterator();
                    String currentRuleNodeId = alarmMessage.currentRuleNodeId;

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
                                sendToNext(className, context, toRuleNode, toId, ruleChain, null);
                            }

                        }
                    }
                    log.info("true");
                })
                .build();
    }

    private void sendToNext(String ruleNodeActualClass, IoTContext context, BaseRule toRuleNode, String toId,
                            RuleChain ruleChain, FilterScriptActor.DeviceDataEvent deviceDataEvent) {
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
            case "com.yq.rule.node.external.RestHttpRuleNode":
                Map<String, Object> sensorDataMap = new HashMap<>();
                sensorDataMap.put("temperature", 610);
                sensorDataMap.put("humidity", 20);
                FilterScriptActor.DeviceDataEvent deviceDataAndRule =
                        new FilterScriptActor.DeviceDataEvent("device001", sensorDataMap, toId, ruleChain);

                context.getRestActor().tell(deviceDataAndRule, getSelf());
                break;

            default:
                log.info("no found root rule Node class={}", ruleNodeActualClass);
        }
    }
}

