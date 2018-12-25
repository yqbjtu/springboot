package com.yq.rule.node;

import com.yq.rule.BaseRule;
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
    public void init() {
        log.info("FilterRule init");
    };

    public void process() {
        log.info("FilterRule process");
    };

    public void destroy() {
        log.info("FilterRule destroy");
    };

}
