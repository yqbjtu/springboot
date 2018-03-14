package com.yq.demo.service;


import com.yq.demo.dao.AgentPoolAgentMapJpaRepository;
import com.yq.demo.entity.AgentPoolAgentMap;


public class AgentPoolAgentMapService {
    private AgentPoolAgentMapJpaRepository agentPoolAgentMapJpaRepo;

    public void addAgentPoolAgentMap(AgentPoolAgentMap agentPoolAgentMap) {
        agentPoolAgentMapJpaRepo.save(agentPoolAgentMap);
    }

    public void delAgentsMapByPoolUuid(String poolUuid) {
        agentPoolAgentMapJpaRepo.deleteByAgentPoolUuid(poolUuid);
    }

}
