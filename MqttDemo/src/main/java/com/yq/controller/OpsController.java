

package com.yq.controller;


import com.alibaba.fastjson.JSONObject;
import com.yq.service.MyMqttClient;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/user")
@Slf4j
public class OpsController {

    @Autowired
    MyMqttClient myMqttClient;

    @ApiOperation(value = "按照topic订阅", notes="private")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "topic", defaultValue = "topic01", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "clientId", defaultValue = "yqClient1", required = true, dataType = "string", paramType = "query")
    })
    @GetMapping(value = "/sub", produces = "application/json;charset=UTF-8")
    public String subscribe(@RequestParam String topic, @RequestParam String clientId) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("currentTime", LocalDateTime.now().toString());

        String result = myMqttClient.subscribe(topic, clientId);
        jsonObj.put("result", result);
        return jsonObj.toString();
    }

    @ApiOperation(value = "发布消息到topic", notes="private")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "topic", defaultValue = "topic01", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "msg", defaultValue = "{\"sensor1\":\"abc\"}", required = true, dataType = "string", paramType = "query"),
    })
    public String publish(@RequestParam String topic, @RequestParam String msg) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("currentTime", LocalDateTime.now().toString());

        return jsonObj.toString();
    }
}