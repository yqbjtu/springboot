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
public class FilterRule extends BaseRule {
    String nodeActualClass  = FilterRule.class.getCanonicalName();
    @Override
    void init() {
        log.info("FilterRule init");
    };
    void process() {
        log.info("FilterRule process");
    };

    void destroy() {
        log.info("FilterRule destroy");
    };

}
