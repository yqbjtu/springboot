package com.yq.rule;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple to Introduction
 * className: RuleChain
 *
 * @author EricYang
 * @version 2018/12/24 15:51
 */
@Data
public class RuleChain {
    private String rootRuleNodeId;
    //uuid
    private String id;
    private List<BaseRule> ruleList = new ArrayList<>();
    private List<RuleRelation> relationList = new ArrayList<>();

    public void addRuleNode(BaseRule baseRule) {
        ruleList.add(baseRule);
    }
}
