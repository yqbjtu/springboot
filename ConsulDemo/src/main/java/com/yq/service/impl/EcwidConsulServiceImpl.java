package com.yq.service.impl;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.agent.model.NewService;
import com.ecwid.consul.v1.health.model.HealthService;
import com.ecwid.consul.v1.kv.model.GetValue;
import com.yq.config.ConsulConfig;
import com.yq.service.IConsulService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Simple to Introduction

 *  本代码只是为了说明如何使用consul api，没有使用接口让两个service都集成接口
 * @version 2018/9/1 10:01
 */

@Service
@Slf4j
@Component
public class EcwidConsulServiceImpl implements IConsulService {
    @Autowired
    private ConsulConfig consulConfig;

    private ConsulClient client = null;

    public EcwidConsulServiceImpl() {
        log.info("consulConfig={}", consulConfig);
        //client = new ConsulClient(consulConfig.getConsulIP(), consulConfig.getConsulPort());
        client = new ConsulClient("127.0.0.1", 8500);
    }

    @Override
    public void registerService(String serviceName, String serviceId) {
        // register new service
        NewService newService = new NewService();
        newService.setId(serviceId);
        newService.setName(serviceName);
        newService.setTags(Arrays.asList("EU-West", "EU-East"));
        newService.setPort(8080);

        NewService.Check serviceCheck = new NewService.Check();
        serviceCheck.setHttp("http://127.0.0.1:8080/health");
        serviceCheck.setInterval("10s");
        newService.setCheck(serviceCheck);
        client.agentServiceRegister(newService);
    }

    @Override
    public List<HealthService> findHealthyService(String serviceName) {
        Response<List<HealthService>> healthyServices = client.getHealthServices(serviceName, true, QueryParams.DEFAULT);
        return healthyServices.getValue();
    }

    @Override
    public void storeKV(String key, String value) {
        Response<Boolean> booleanResponse = client.setKVValue(key, value);
    }

    @Override
    public String getKV(String key) {
        Response<GetValue> getValueResponse = client.getKVValue(key);
        //return getValueResponse.getValue().getValue();
        return getValueResponse.getValue().getDecodedValue();
    }

    @Override
    public List<String> findRaftPeers() {
        Response<List<String>> listResponse = client.getStatusPeers();
        return listResponse.getValue();
    }

    @Override
    public String findRaftLeader() {
        Response<String> stringResponse = client.getStatusLeader();
        return stringResponse.getValue();
    }
}

