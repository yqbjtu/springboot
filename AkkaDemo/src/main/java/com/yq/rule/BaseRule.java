package com.yq.rule;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple to Introduction
 * className: BaseRule
 *
 * @author EricYang
 * @version 2018/12/24 15:52
 */
@Data
public abstract class BaseRule {
    String type;
    //id号在每个规则链中不同， 例如filterRule在第一个规则链和第二个规则链是不同一个，也可能相同。
    String id;
    String description;
    protected String content;
    String nodeActualClass;
    //例如， 本规则的id是5， 它有三个向外的输出，分别是 true 9, failure 10, false 13号， {5,9}, {5.10}, {5，13}， 这个数组存储的是类似(5, true, 9)，
    List<RuleRelation> relationList = new ArrayList<>();

    public void init() {};
    public void process() {};
    public void destroy() {};

    public void addRuleRelation(RuleRelation relationType) {
        relationList.add(relationType);
    }
}