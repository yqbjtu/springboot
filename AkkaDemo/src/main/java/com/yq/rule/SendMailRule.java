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
public class SendMailRule extends BaseRule {
    String nodeActualClass  = SendMailRule.class.getCanonicalName();
    @Override
    void init() {
        log.info("SendMailRule init");
    };
    void process() {
        String alarmContent = this.content;
        log.info("SendMailRule process");
    };

    void destroy() {
        log.info("SendMailRule destroy");
    };

}
