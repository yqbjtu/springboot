package com.yq.service;

import com.orbitz.consul.*;
import com.orbitz.consul.model.health.ServiceHealth;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;


@Service
public class ConsulService {
    private Consul consul = null;

    public ConsulService() {
        consul = Consul.builder().withUrl("http://x.y.z:8500").build();
    }


    /**
     * 注册服务
     * 并对服务进行健康检查
     * servicename唯一
     * serviceId:没发现有什么作用
     */
    public void registerService(String serviceName, String serviceId) {

        AgentClient agentClient = consul.agentClient();        //建立AgentClient

        try {
            /**
             * 注意该注册接口：
             * 需要提供一个健康检查的服务URL，以及每隔多长时间访问一下该服务（这里是3s）
             */
            agentClient.register(8080, URI.create("http://127.0.0.1:8080/health").toURL(), 10L, serviceName, serviceId, "dev");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
//        try {
//            agentClient.pass(serviceId);//健康检查
//        } catch (NotRegisteredException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 发现可用的服务
     */
    public List<ServiceHealth> findHealthyService(String servicename){

        HealthClient healthClient = consul.healthClient();//获取所有健康的服务
        return healthClient.getHealthyServiceInstances(servicename).getResponse();//寻找passing状态的节点
    }

    /**
     * 存储KV
     */
    public void storeKV(String key, String value){
        KeyValueClient kvClient = consul.keyValueClient();
        kvClient.putValue(key, value);//存储KV
    }

    /**
     * 根据key获取value
     */
    public String getKV(String key){
        KeyValueClient kvClient = consul.keyValueClient();
        return kvClient.getValueAsString(key).get();
    }

    /**
     * 找出一致性的节点（应该是同一个DC中的所有server节点）
     */
    public List<String> findRaftPeers(){
        StatusClient statusClient = consul.statusClient();
        return statusClient.getPeers();
    }

    /**
     * 获取leader
     */
    public String findRaftLeader(){
        StatusClient statusClient = consul.statusClient();
        return statusClient.getLeader();
    }

}