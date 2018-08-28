package com.yq.controller;

import java.util.List;

import com.yq.service.ConsulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.orbitz.consul.model.health.ServiceHealth;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("consul API")
@RestController
@RequestMapping("/consul")
public class ConsulController {
    @Autowired
    private ConsulService consulService;

    /*******************************服务注册与发现*******************************/
    @ApiOperation("register service")
    @RequestMapping(value="/regSvc/{servicename}/{serviceid}",method=RequestMethod.POST)
    public void registerService(@PathVariable("servicename") String serviceName,
                                @PathVariable("serviceid") String serviceId) {
        consulService.registerService(serviceName, serviceId);
    }

    @ApiOperation("dicover service")
    @RequestMapping(value="/disSvc/{servicename}",method=RequestMethod.GET)
    public List<ServiceHealth> discoverService(@PathVariable("servicename") String serviceName) {
        return consulService.findHealthyService(serviceName);
    }

    /*******************************KV*******************************/
    @ApiOperation("store KV")
    @RequestMapping(value="/kv/{key}/{value}",method=RequestMethod.POST)
    public void storeKV(@PathVariable("key") String key,
                        @PathVariable("value") String value) {
        consulService.storeKV(key, value);
    }

    @ApiOperation("get KV")
    @RequestMapping(value="/kv/{key}",method=RequestMethod.GET)
    public String getKV(@PathVariable("key") String key) {
        return consulService.getKV(key);
    }

    /*******************************server*******************************/
    @ApiOperation("获取同一个DC中的所有server节点")
    @RequestMapping(value="/raftpeers",method=RequestMethod.GET)
    public List<String> findRaftPeers() {
        return consulService.findRaftPeers();
    }

    @ApiOperation("获取leader")
    @RequestMapping(value="/leader",method=RequestMethod.GET)
    public String leader() {
        return consulService.findRaftLeader();
    }
}
