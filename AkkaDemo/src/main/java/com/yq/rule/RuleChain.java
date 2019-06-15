package com.yq.rule;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Map<String, BaseRule> nodeIdRuleNodeMap = new HashMap<>();

    public void addRuleNode(BaseRule baseRule) {
        ruleList.add(baseRule);
    }

    public void putMapEntry(String nodeId, BaseRule baseRule) {
        nodeIdRuleNodeMap.put(nodeId, baseRule);
    }
}
