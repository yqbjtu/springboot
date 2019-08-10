package com.yq.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yq.domain.Device;
import com.yq.service.RateLimitSvc;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple to Introduction
 * className: MyController
 *
 * @author EricYang
 * @version 2019/8/10 21:19
 */


@RestController
@RequestMapping
public class MyController {

    //实际上我们一般不会再controller中直接进行rateLimit， 而是在网关处，根据租户id，用户id，应用id，或者ip进行限流。
    //本程序只是RateLimit的例子，不建议直接在生产代码中使用
    @Autowired
    private RateLimitSvc rateLimitSvc;


    @ApiOperation(value = "按用户id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tenantId", value = "tenantId", defaultValue = "001", required = true, dataType = "string", paramType = "path"),
    })
    @GetMapping(value = "/tenants/{tenantId}", produces = "application/json;charset=UTF-8")
    public String getDevice(@PathVariable String tenantId) {
        if (rateLimitSvc.execRateLimit(tenantId)) {
            //这里实际中应该是调用设备服务查询数据库，本示例为了简化直接new了一个对象
            Device device = new Device();
            device.setId("001");
            device.setName("一号设备");
            return JSONObject.toJSONString(device, SerializerFeature.WriteMapNullValue);
        } else {
            JSONObject json = new JSONObject();
            json.put("errMsg", "too many requests");
            return json.toJSONString();
        }
    }
}