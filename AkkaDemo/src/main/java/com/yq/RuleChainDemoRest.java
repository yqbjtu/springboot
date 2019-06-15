package com.yq;

import com.yq.rule.RuleChain;
import com.yq.rule.RuleRelation;
import com.yq.rule.node.action.CreateAlarmRule;
import com.yq.rule.node.action.SendMailRule;
import com.yq.rule.node.external.RestHttpRuleNode;
import com.yq.rule.node.filter.FilterScriptRuleNode;
import lombok.Data;

/**
 * Simple to Introduction
 * className: RuleChainDemo1
 *
 * @author EricYang
 * @version 2018/12/25 11:23
 */
@Data
public class RuleChainDemoRest {
    private RuleChain ruleChain = new RuleChain();

    RuleChainDemoRest() {
        init();
    }

    private void init() {
        FilterScriptRuleNode filterRule = new FilterScriptRuleNode();
        filterRule.setId("001");
        filterRule.setType("FilterType");
        filterRule.setContent("return msg.temperature < -40 || msg.temperature > 80 || msg.humidity > 30;");


        CreateAlarmRule createAlarmRule = new CreateAlarmRule();
        createAlarmRule.setId("002");
        createAlarmRule.setType("ActionType");
        createAlarmRule.setContent("send alarm to DB002");

        SendMailRule sendMailRule = new SendMailRule();
        sendMailRule.setId("003");
        sendMailRule.setType("ActionType");
        sendMailRule.setContent("send mail to user 003");

        RestHttpRuleNode restNode = new RestHttpRuleNode();
        restNode.setId("004");
        restNode.setType("ExternalType");
        restNode.setContent("send mail to user {deviceName}");
        restNode.setUrl("http://www.v.com/dbMsg");
        restNode.setMethod("post");
        restNode.setUsername("user1");
        restNode.setPassword("passw0rd!");

        //filter-----true---> CreateAlarmRule
        //filter-----true---> relation2
        RuleRelation relation1 = new RuleRelation();
        relation1.setFromId("001");
        relation1.setToId("002");
        relation1.setRelationType("true");
        filterRule.addRuleRelation(relation1);

        RuleRelation relation2 = new RuleRelation();
        relation2.setFromId("001");
        relation2.setToId("003");
        relation2.setRelationType("true");
        filterRule.addRuleRelation(relation2);

        //alarmMessage---true ---restRuleNode
        RuleRelation relationAlarm2Rest = new RuleRelation();
        relationAlarm2Rest.setFromId("002");
        relationAlarm2Rest.setToId("004");
        relationAlarm2Rest.setRelationType("true");
        createAlarmRule.addRuleRelation(relationAlarm2Rest);

        ruleChain.addRuleNode(filterRule);
        ruleChain.addRuleNode(createAlarmRule);
        ruleChain.addRuleNode(sendMailRule);
        ruleChain.addRuleNode(restNode);

        //find all rules and send device sensor data
        ruleChain.setRootRuleNodeId("001");
    }
}
