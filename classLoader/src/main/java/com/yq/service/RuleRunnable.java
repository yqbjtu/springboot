package com.yq.service;

import com.yq.domain.User;
import com.yq.util.DroolsUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Slf4j
@Data
public class RuleRunnable implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RuleRunnable.class);

    private Map<String, Object> dataMap = null;
    private String userId = null;

    public RuleRunnable(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
        log.debug("RuleRunnable init.");
    }

    @Override
    public void run() {
        long threadId = Thread.currentThread().getId();
        userId = (String) dataMap.get("userId");
        long begin = System.currentTimeMillis();

        log.debug("start for userId={}, threadId={}", userId, threadId);

        DroolsProcessor processor = new DroolsProcessor();

        List<User> list = new ArrayList<>();
        String ruleContent = "this[\"S1\"]>10";
        String head = DroolsUtils.makeRuleHead();
        String body = DroolsUtils.makeRuleBody(ruleContent);
        String fullDroolRule = head + body;
        try {
            processor.initProcessor(userId, fullDroolRule);
            dataMap.put("S1", 20);
            processor.process(dataMap, list);
        } catch (Exception ex) {
            log.error("exception. userId={}, threadId={}", userId, threadId, ex);
            return;
        }

        for (Object obj : list) {
            User u2 = new User();
            BeanUtils.copyProperties(obj, u2);
            log.info("obj={}", obj);
            try {
                ClassLoader classLoader = obj.getClass().getClassLoader();
                String objClassName = obj.getClass().getCanonicalName();
                if (obj instanceof User) {
                    log.info("drools uses loader={}, objClassName={}, threadId={}", classLoader, objClassName, threadId);
                } else {
                    ClassLoader userClassLoader = User.class.getClassLoader();
                    log.info("objLoader={}, userLoader={}, objClassName={},threadId={}", classLoader, userClassLoader, objClassName, threadId);
                }
            } catch (Exception ex) {
                log.debug("not the same class", ex);
            }
        }

        long end = System.currentTimeMillis();
        log.debug("end for userId={}, cost={}, threadId={}", userId, end - begin, threadId);
    }

}
