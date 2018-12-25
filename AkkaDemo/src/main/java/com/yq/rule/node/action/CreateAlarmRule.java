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
public class CreateAlarmRule extends BaseRule {

    @Override
    public void init() {
        log.info("CreateAlarmRule init");
    };

    @Override
    public void process() {
        String alarmContent = this.content;
        log.info("CreateAlarmRule process");
    };

    @Override
    public void destroy() {
        log.info("CreateAlarmRule destroy");
    };

}
