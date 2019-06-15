package com.yq.rule.node.external;

import com.yq.rule.BaseRule;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple to Introduction
 * className: FilterScriptRuleNode
 *
 * @author EricYang
 * @version 2018/12/24 15:52
 */

@Slf4j
@Data
public class RestHttpRuleNode extends BaseRule {
    private String url;
    private String method;
    private Map<String, String> headerMap = new HashMap<>();
    private String username;
    private String password;

    @Override
    public void init() {
        log.info("RestHttpRuleNode init");
    };

    public void process() {
        log.info("call url={} with username={} and password{}, payload={}", url, username, password, this.content);
    };

    public void destroy() {
        log.info("RestHttpRuleNode destroy");
    };

}
