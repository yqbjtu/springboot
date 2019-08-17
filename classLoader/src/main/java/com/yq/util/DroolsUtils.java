package com.yq.util;

import com.yq.domain.User;
import com.yq.service.DroolsProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
public class DroolsUtils {
    private static String packageName = "org.drools.demo;";

    private static List<String> importPackages = Arrays.asList(
            "import org.slf4j.Logger;"
            ,"import org.slf4j.LoggerFactory;"
            ,"import com.yq.service.RuleRunnable;"
            ,"import com.yq.domain.User;"
            ,"import java.util.Map;"
            ,"import java.util.HashMap;"
            ,"import java.util.List;"
            ,"global java.util.List list");

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        Map<String, Object> dataMap = new HashMap<>();

        DroolsProcessor processor = new DroolsProcessor();
        String ruleContent = "this[\"S1\"]>10";
        String head = makeRuleHead();
        String body = makeRuleBody(ruleContent);
        String fullDroolRule = head + body;
        try {
            processor.initProcessor("u001", fullDroolRule);
            dataMap.put("S1", 20);
            processor.process(dataMap, list);
        } catch (Exception ex) {
            log.error("exception", ex);
            return;
        }
        for (Object obj : list) {
            User u2 = new User();
            BeanUtils.copyProperties(obj, u2);
            log.info("obj={}", obj);
            try {
                ClassLoader classLoader = obj.getClass().getClassLoader();
                if (obj instanceof User) {
                    log.info("drools uses the restart class loader={}", classLoader);
                } else {
                    ClassLoader userClassLoader = User.class.getClass().getClassLoader();
                    log.info("objClassLoader={}, userClassLoader={}", classLoader, userClassLoader);
                }
            } catch (Exception ex) {
                log.debug("not same class", ex);
            }
        }
        log.info("list size={}", list.size());
    }


    public static String makeRuleHead() {
        StringBuilder sb = new StringBuilder();
        //规则头部
        sb.append("package ").append(packageName).append("\n");
        importPackages.forEach(t -> sb.append(t).append("\n"));
        return sb.toString();
    }

    public static String makeRuleBody(String content){
        StringBuilder sb = new StringBuilder();
        //规则名称
        sb.append("\n").append("rule  \"").append("001\"").append(" salience 1 \n");
        //规则条件
        sb.append("when").append("\n");

        sb.append(" $map").append(": Map( $msg:(");
        sb.append(content).append(")");
        sb.append("==true )\n");

        sb.append("then \n");

        sb.append("Logger logger = LoggerFactory.getLogger(RuleRunnable.class);\n");
        sb.append("logger.info(\"rule userId=\"+ $map.get(\"userId\"));\n");
        sb.append(" ClassLoader innerUserClassLoader = User.class.getClassLoader();\n");
        sb.append(" ClassLoader innerlistClassLoader = list.getClass().getClassLoader();\n");
        sb.append(" ClassLoader innerThreadClassLoader = Thread.currentThread().getContextClassLoader() ;\n");
        sb.append(" long threadId = Thread.currentThread().getId();\n");
        sb.append("logger.info(\"rule UserLoader={}, listLoader={}, threadLoader={}, thread={}\", " +
                "innerUserClassLoader, innerlistClassLoader, innerThreadClassLoader, threadId);\n");

        sb.append("User user = new User();").append("\n");
        sb.append("user.setId($map.get(\"userId\")+\"\");").append("\n");
        sb.append("list.add(user);");

//        sb.append("Class<?> u2Class = innerThreadClassLoader.loadClass(\"com.yq.domain.User\");").append("\n");
//        sb.append("User user2 = (User)u2Class.newInstance();").append("\n");
//        sb.append("user2.setId(\"002\");").append("\n");
//        sb.append("list.add(user2);");

        sb.append("\n").append("end");
        return sb.toString();
    }

}
