package com.yq;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.yq.actor.ClearAlarmAction;
import com.yq.actor.CreateAlarmAction;
import com.yq.actor.FilterScript;
import com.yq.rule.BaseRule;
import com.yq.rule.CreateAlarmRule;
import com.yq.rule.FilterRule;
import com.yq.rule.RuleChain;
import com.yq.rule.RuleRelation;
import com.yq.rule.SendMailRule;
import com.yq.ruleActor.CreateAlarmActionActor;
import com.yq.ruleActor.FilterScriptActor;
import com.yq.ruleActor.SendMailActionActor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class AkkaAlarmRuleChainDemo {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("AlarmDemo");
        try {

            final ActorRef createAlarmActor =
                    system.actorOf(CreateAlarmActionActor.props(), "createAlarmActionActor");
            final ActorRef sendMailActor =
                    system.actorOf(SendMailActionActor.props(), "sendMailActor");
            final ActorRef filterScript =
                    system.actorOf(FilterScriptActor.props("filterScriptActor", createAlarmActor, clearAlarmActor), "filterScript");


            //#create-actors

            //#main-send-messages
            Map<String, Object> sensorDataMap = new HashMap<>();
            sensorDataMap.put("temperature", 60);
            sensorDataMap.put("humidity", 20);


            RuleChain ruleChainOne = new RuleChain();

            FilterRule filterRule = new FilterRule();
            filterRule.setId("001");
            filterRule.setType("FilterType");


            CreateAlarmRule createAlarmRule = new CreateAlarmRule();
            createAlarmRule.setId("002");
            createAlarmRule.setType("ActionType");
            createAlarmRule.setContent("send alarm to DB");

            SendMailRule sendMailRule = new SendMailRule();
            sendMailRule.setId("003");
            sendMailRule.setType("ActionType");
            sendMailRule.setContent("send mail to user");

            RuleRelation relation1 = new RuleRelation();
            relation1.setFromId("001");
            relation1.setFromId("002");
            relation1.setRelationType("true");
            filterRule.addRuleRelation(relation1);

            RuleRelation relation2 = new RuleRelation();
            relation2.setFromId("001");
            relation2.setFromId("003");
            relation2.setRelationType("false");
            filterRule.addRuleRelation(relation1);


            ruleChainOne.addRuleNode(filterRule);
            ruleChainOne.addRuleNode(createAlarmRule);
            ruleChainOne.addRuleNode(sendMailRule);

            //find all rules and send device sensor data
            ruleChainOne.setRootRuleNodeId("001");

            //得到第一个RuleNode
            List<BaseRule> ruleList = ruleChainOne.getRuleList();
            Iterator<BaseRule> itr = ruleList.iterator();
            String rootRuleNodeId = ruleChainOne.getRootRuleNodeId();
            while(itr.hasNext()) {
                BaseRule baseRule = itr.next();
                if (Objects.equals(rootRuleNodeId, baseRule.getId())) {
                    String ruleNodeType = baseRule.getType();
                    String ruleNodeActualClass = baseRule.getNodeActualClass();

                    FilterScript.DeviceDataEvent deviceDataAndRule = new FilterScript.DeviceDataEvent("device001", sensorDataMap);
                    switch (ruleNodeActualClass) {
                        case "com.yq.rule.FilterRule":
                            filterScript.tell(deviceDataAndRule, ActorRef.noSender());
                            break;
                        case "com.yq.rule.CreateAlarmRule":
                            createAlarmActor.tell(deviceDataAndRule, ActorRef.noSender());
                            break;
                        default:
                            log.info("no root rule Node");

                    }

                    break;
                }
            }


            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        } catch (IOException ioe) {
        } finally {
            system.terminate();
        }
    }
}
