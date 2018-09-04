package com.yq.service;

import com.ecwid.consul.v1.health.model.HealthService;
import com.orbitz.consul.model.health.ServiceHealth;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Simple to Introduction
 * className: IConsulService
 *
 * @author EricYang
 * @version 2018/9/1 10:21
 */

public interface IConsulService {

    public void registerService(String serviceName, String serviceId) ;

    //Orbitz 和Ecwid提供的监控节点类型不同，这里也不进行二次封装了，因此使用接口的默认实现方法
    // for orbitz
    public default List<ServiceHealth> findServiceHealthy(String serviceName){
        return null;
    }

    // for ecwid
    public default List<HealthService> findHealthyService(String serviceName, boolean onlyPassing){
        return null;
    }


    public void storeKV(String key, String value);

    public String getKV(String key);

    public List<String> findRaftPeers();

    public String findRaftLeader();
}