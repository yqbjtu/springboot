package com.yq;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;


import com.yq.context.IoTContext;
import com.yq.rule.BaseRule;
import com.yq.rule.RuleChain;
import com.yq.ruleActor.CreateAlarmActionActor;
import com.yq.ruleActor.FilterScriptActor;
import com.yq.ruleActor.RestHttpActor;
import com.yq.ruleActor.SendMailActionActor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

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
            IoTContext ioTContext = new IoTContext();

            final ActorRef createAlarmActor =
                    system.actorOf(CreateAlarmActionActor.props(ioTContext), "createAlarmActionActor");
            final ActorRef sendMailActor =
                    system.actorOf(SendMailActionActor.props(ioTContext), "sendMailActor");
            final ActorRef callRestActor =
                    system.actorOf(RestHttpActor.props(ioTContext), "callRestActor");


            ioTContext.setCreateAlarmActor(createAlarmActor);
            ioTContext.setSendMailActor(sendMailActor);
            ioTContext.setRestActor(callRestActor);

            final ActorRef filterScript =
                    system.actorOf(FilterScriptActor.props(ioTContext), "filterScript");


            Map<String, Object> sensorDataMap = new HashMap<>();
            sensorDataMap.put("temperature", 610);
            sensorDataMap.put("humidity", 20);


            RuleChainDemo1 demo1 = new RuleChainDemo1();
            RuleChainDemo2 demo2 = new RuleChainDemo2();
            RuleChainDemoRest restDemo = new RuleChainDemoRest();
            //RuleChain ruleChain = demo1.getRuleChain();
            //RuleChain ruleChain = demo2.getRuleChain();
            RuleChain ruleChain = restDemo.getRuleChain();


            //得到第一个RuleNode
            List<BaseRule> ruleList = ruleChain.getRuleList();
            Iterator<BaseRule> itr = ruleList.iterator();
            String rootRuleNodeId = ruleChain.getRootRuleNodeId();
            Map<String, BaseRule> nodeIdRuleNodeMap = ruleChain.getNodeIdRuleNodeMap();

            String ruleNodeType = null;
            String ruleNodeActualClass = null;
            while(itr.hasNext()) {

                BaseRule baseRule = itr.next();
                ruleChain.putMapEntry(baseRule.getId(), baseRule);
                if (Objects.equals(rootRuleNodeId, baseRule.getId())) {
                    ruleNodeType = baseRule.getType();
                    ruleNodeActualClass = baseRule.getClass().getCanonicalName();
                }
            }

            if (StringUtils.isNotBlank(ruleNodeActualClass)) {
                FilterScriptActor.DeviceDataEvent deviceDataAndRule =
                        new FilterScriptActor.DeviceDataEvent("device001", sensorDataMap, "001", ruleChain);
                switch (ruleNodeActualClass) {
                    case "com.yq.rule.node.filter.FilterScriptRuleNode":
                        filterScript.tell(deviceDataAndRule, ActorRef.noSender());
                        break;
                    case "com.yq.rule.node.action.CreateAlarmRule":
                        createAlarmActor.tell(deviceDataAndRule, ActorRef.noSender());
                        break;
                    case "com.yq.rule.node.action.SendMailRule":
                        sendMailActor.tell(deviceDataAndRule, ActorRef.noSender());
                        break;
                    default:
                        log.warn("no found root rule Node class={}", ruleNodeActualClass);
                }
            }
            else {
                log.warn("no root rule Node");
            }



            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        } catch (IOException ioe) {
        } finally {
            system.terminate();
        }
    }
}
