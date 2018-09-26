package com.yq.controller;

import com.alibaba.fastjson.JSONObject;
import com.ecwid.consul.v1.health.model.HealthService;
import com.yq.service.IConsulService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Api("consul API")
@RestController
@RequestMapping("/consul2")
public class EcwidConsulController {

    @Autowired
    @Qualifier("ecwidConsulServiceImpl")
    private IConsulService consulService;

    @ApiOperation("register service")
    @PostMapping(value="/regSvc/{svcName}/{svcId}", produces = "application/json;charset=UTF-8")
    public void registerService(@PathVariable("svcName") String svcName,
                                @PathVariable("svcId") String svcId) {
        consulService.registerService(svcName, svcId);
    }

    @ApiOperation("deRegister service")
    @PostMapping(value="/deRegSvc/{svcId}", produces = "application/json;charset=UTF-8")
    public void deRegisterService(@PathVariable("svcId") String svcId) {
        consulService.deRegisterService(svcId);
    }

    @ApiOperation("discover service")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "svcName", defaultValue = "svcName", value = "svcName", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "onlyPassing", defaultValue = "true", value = "onlyPassing", required = true, dataType = "boolean", paramType = "query")
    })
    @GetMapping(value="/disSvc/{svcName}", produces = "application/json;charset=UTF-8")
    public String discoverService(@PathVariable("svcName") String svcName, @RequestParam boolean onlyPassing) {
        List<HealthService> list = consulService.findHealthyService(svcName,onlyPassing);
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("currentTime", LocalDateTime.now().toString());
        jsonObj.put("list", list);
        jsonObj.put("size", list.size());
        return jsonObj.toJSONString();
    }

    @ApiOperation("store KV")
    @PostMapping(value="/kv/{key}/{value}",produces = "application/json;charset=UTF-8")
    public void storeKV(@PathVariable("key") String key,
                        @PathVariable("value") String value) {
        consulService.storeKV(key, value);
    }

    @ApiOperation("get KV")
    @GetMapping(value="/kv/{key}", produces = "application/json;charset=UTF-8")
    public String getKV(@PathVariable("key") String key) {
        return consulService.getKV(key);
    }


    @ApiOperation("获取同一个DC中的所有server节点")
    @GetMapping(value="/raftpeers", produces = "application/json;charset=UTF-8")
    public List<String> findRaftPeers() {
        return consulService.findRaftPeers();
    }

    @ApiOperation("获取leader")
    @GetMapping(value="/leader", produces = "application/json;charset=UTF-8")
    public String leader() {
        return consulService.findRaftLeader();
    }
}
