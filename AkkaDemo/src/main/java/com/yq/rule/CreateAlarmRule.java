package com.yq.rule;

import lombok.extern.slf4j.Slf4j;

/**
 * Simple to Introduction
 * className: FilterRule
 *
 * @author EricYang
 * @version 2018/12/24 15:52
 */

@Slf4j
public class CreateAlarmRule extends BaseRule {
    String nodeActualClass  = CreateAlarmRule.class.getCanonicalName();

    @Override
    void init() {
        log.info("CreateAlarmRule init");
    };
    void process() {
        String alarmContent = this.content;
        log.info("CreateAlarmRule process");
    };

    void destroy() {
        log.info("CreateAlarmRule destroy");
    };

}
