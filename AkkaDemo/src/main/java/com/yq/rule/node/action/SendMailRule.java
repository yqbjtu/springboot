package com.yq.rule.node.action;

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
public class SendMailRule extends BaseRule {
    String nodeActualClass  = SendMailRule.class.getCanonicalName();
    @Override
    public void init() {
        log.info("SendMailRule init");
    };
    public void process() {
        String alarmContent = this.content;
        log.info("SendMailRule process");
    };

    public void destroy() {
        log.info("SendMailRule destroy");
    };

}
