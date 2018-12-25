package com.yq;

import com.yq.rule.RuleChain;
import com.yq.rule.RuleRelation;
import com.yq.rule.node.filter.FilterScriptRuleNode;
import com.yq.rule.node.action.CreateAlarmRule;
import com.yq.rule.node.action.SendMailRule;
import lombok.Data;

/**
 * Simple to Introduction
 * className: RuleChainDemo1
 *
 * @author EricYang
 * @version 2018/12/25 11:23
 */
@Data
public class RuleChainDemo1 {
    private RuleChain ruleChain = new RuleChain();

    RuleChainDemo1() {
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
        sendMailRule.setContent("send mail to user");

        RuleRelation relation1 = new RuleRelation();
        relation1.setFromId("001");
        relation1.setToId("002");
        relation1.setRelationType("true");
        filterRule.addRuleRelation(relation1);

        RuleRelation relation2 = new RuleRelation();
        relation2.setFromId("001");
        relation2.setToId("003");
        relation2.setRelationType("false");
        filterRule.addRuleRelation(relation2);


        ruleChain.addRuleNode(filterRule);
        ruleChain.addRuleNode(createAlarmRule);
        ruleChain.addRuleNode(sendMailRule);

        //find all rules and send device sensor data
        ruleChain.setRootRuleNodeId("001");
    }
}
