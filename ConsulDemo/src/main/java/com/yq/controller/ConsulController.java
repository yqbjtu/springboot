package com.yq.controller;

import com.alibaba.fastjson.JSONObject;
import com.ecwid.consul.v1.health.model.HealthService;
import com.orbitz.consul.model.health.ServiceHealth;
import com.yq.service.IConsulService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Api("consul API")
@RestController
@RequestMapping("/consul1")
public class ConsulController {
    @Autowired
    @Qualifier("orbitzConsulServiceImpl")
    private IConsulService consulService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ConsulDiscoveryClient consulDiscoveryClient;


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
    @GetMapping(value="/disSvc1/{svcName}", produces = "application/json;charset=UTF-8")
    public String discoverService(@PathVariable("svcName") String svcName) {
        List<ServiceHealth> list = consulService.findServiceHealthy(svcName);

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("currentTime", LocalDateTime.now().toString());
        jsonObj.put("normallist", list);
        jsonObj.put("size", list.size());
        return jsonObj.toJSONString();
    }

    @ApiOperation("discover service by consulDiscoveryClient")
    @GetMapping(value="/disSvc3/{svcName}", produces = "application/json;charset=UTF-8")
    public String discoverServiceByConsulClient(@PathVariable("svcName") String svcName) {
        List<ServiceInstance> list = consulDiscoveryClient.getInstances(svcName);

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("currentTime", LocalDateTime.now().toString());
        jsonObj.put("consullist", list);
        jsonObj.put("size", list.size());
        return jsonObj.toJSONString();
    }

    @ApiOperation("discover service by discoveryClient")
    @GetMapping(value="/disSvc2/{svcName}", produces = "application/json;charset=UTF-8")
    public String discoverServiceByClient(@PathVariable("svcName") String svcName) {
        List<ServiceInstance> list = discoveryClient.getInstances(svcName);

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("currentTime", LocalDateTime.now().toString());
        jsonObj.put("list", list);
        jsonObj.put("size", list.size());
        return jsonObj.toJSONString();
    }

    @ApiOperation("store KV")
    @PostMapping(value="/kv/{key}/{value}", produces = "application/json;charset=UTF-8")
    public void storeKV(@PathVariable("key") String key,
                        @PathVariable("value") String value) {
        consulService.storeKV(key, value);
    }

    @ApiOperation("get KV")
    @PostMapping(value="/kv/{key}", produces = "application/json;charset=UTF-8")
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
