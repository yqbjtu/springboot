package com.yq.rule.node.filter;

import com.yq.rule.BaseRule;
import lombok.extern.slf4j.Slf4j;

/**
 * Simple to Introduction
 * className: FilterScriptRuleNode
 *
 * @author EricYang
 * @version 2018/12/24 15:52
 */

@Slf4j
public class EventSwitchRuleNode extends BaseRule {
    String nodeActualClass  = EventSwitchRuleNode.class.getCanonicalName();

    @Override
    public void init() {
        log.info("FilterScriptRuleNode init");
    };

    public void process() {
        log.info("FilterScriptRuleNode process");
    };

    public void destroy() {
        log.info("FilterScriptRuleNode destroy");
    };

}
