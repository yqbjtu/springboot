

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

    @ApiOperation(value = "按照topic订阅(示例代码不会复用MqttClient)", notes="private")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "topic", defaultValue = "topic01", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "clientId", defaultValue = "yqClient1", required = true, dataType = "string", paramType = "query")
    })
    @GetMapping(value = "/sub", produces = "application/json;charset=UTF-8")
    public String subscribe(@RequestParam String topic, @RequestParam String clientId) {
        JSONObject jsonObj = new JSONObject();
        long threadId = Thread.currentThread().getId();
        jsonObj.put("threadId", threadId);
        jsonObj.put("controller", this.toString());
        jsonObj.put("currentTime", LocalDateTime.now().toString());

        String subscribeResult = myMqttClient.subscribe(topic, clientId);
        jsonObj.put("subscribeResult", subscribeResult);

        String result = jsonObj.toString();
        log.info(result);
        return result;
    }

    @ApiOperation(value = "发布消息到topic", notes="private")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "topic", defaultValue = "topic01", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "msg", defaultValue = "{\"deviceId\":\"abc\", \"msg\":{\"data\":{\"sensor1\":\"abc\"}} }", required = true, dataType = "string", paramType = "query"),
    })
    @GetMapping(value = "/pub", produces = "application/json;charset=UTF-8")
    public String publish(@RequestParam String topic, @RequestParam String msg
                          ) {
        JSONObject jsonObj = new JSONObject();
        long threadId = Thread.currentThread().getId();
        jsonObj.put("threadId", threadId);
        jsonObj.put("controller", this.toString());
        jsonObj.put("currentTime", LocalDateTime.now().toString());

        String publishResult = myMqttClient.publish(topic, msg);
        jsonObj.put("publishResult", publishResult);

        String result = jsonObj.toString();
        log.info(result);
        return result;
    }

    @ApiOperation(value = "发布消息到topic 单向ssl连接", notes="private")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "broker", defaultValue = "iot.a.b", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "port", defaultValue = "8883", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "topic", defaultValue = "topic01", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "msg", defaultValue = "{\"deviceId\":\"abc\", \"msg\":{\"data\":{\"sensor1\":\"abc\"}} }", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "username", defaultValue = "username", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "password", defaultValue = "password", required = true, dataType = "string", paramType = "query"),
    })
    @GetMapping(value = "/pubSSL", produces = "application/json;charset=UTF-8")
    public String publishWithSingleSSL(@RequestParam String broker, @RequestParam int port,
                                       @RequestParam String topic, @RequestParam String msg,
                                       @RequestParam String username, @RequestParam String password) {
        JSONObject jsonObj = new JSONObject();
        long threadId = Thread.currentThread().getId();
        jsonObj.put("threadId", threadId);
        jsonObj.put("controller", this.toString());
        jsonObj.put("currentTime", LocalDateTime.now().toString());

        String result = jsonObj.toString();
        log.info(result);
        return result;
    }
}