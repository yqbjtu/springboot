

package com.yq.controller;

import com.alibaba.fastjson.JSONObject;
import com.yq.domain.User;
import com.yq.service.RuleRunnable;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @ApiOperation(value = "测试规则, 演示代码，为了快速展示效果没有按照各种规范去写，仅供说明问题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userID", defaultValue = "001", required = true, dataType = "string", paramType = "path"),
    })
    @GetMapping(value = "/users/{userId}", produces = "application/json;charset=UTF-8")
    public String testRule(@PathVariable String userId) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        log.info("ControllerClassLoader={}", classLoader);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("userId", userId);
        RuleRunnable runnable = new RuleRunnable(dataMap);
        Thread thread1 = new Thread(runnable);
        ClassLoader thread1ClassLoader = thread1.getContextClassLoader();

        log.info("thread1ClassLoader={}, thread1Id={}", thread1ClassLoader, thread1.getId());
        thread1.start();

        Thread thread2 = new Thread(runnable);
        ClassLoader strClassLoader = String.class.getClassLoader();
        ClassLoader ctxThreadClassLoader = Thread.currentThread().getContextClassLoader();

        log.info("strClassLoader={}, ctxThreadClassLoader={}", strClassLoader, ctxThreadClassLoader);
        while(ctxThreadClassLoader.getParent() != null) {
            ctxThreadClassLoader = ctxThreadClassLoader.getParent();
            String name = ctxThreadClassLoader.toString();
            log.info("...pClassLoader={}", ctxThreadClassLoader);
            if (name.contains("AppClassLoader")) {
                break;
            }
        }

        thread2.setContextClassLoader(ctxThreadClassLoader);
        ClassLoader thread2ClassLoader = thread2.getContextClassLoader();

        thread2.start();
        log.info("thread2ClassLoader={}, thread2Id={}", thread2ClassLoader, thread2.getId());

        JSONObject json = new JSONObject();
        json.put("time", LocalDate.now());
        return json.toJSONString();
    }


}