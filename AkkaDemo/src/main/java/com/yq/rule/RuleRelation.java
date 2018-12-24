package com.yq.rule;

import lombok.Data;

/**
 * Simple to Introduction
 * className: RuleRelation
 *
 * @author EricYang
 * @version 2018/12/24 17:12
 */
@Data
public class RuleRelation {
    private String fromId;
    private String toId;
    //true, false, failure
    private String relationType;
}
