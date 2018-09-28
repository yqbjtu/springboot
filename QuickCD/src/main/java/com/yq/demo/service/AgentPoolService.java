package com.yq.demo.service;

import java.util.List;

import com.yq.demo.dao.AgentPoolJpaRepository;
import com.yq.demo.entity.AgentPool;


public class AgentPoolService {
    private AgentPoolJpaRepository agentPoolJpaRepo;

    public long agentpoolCount(){
        return agentPoolJpaRepo.count();
    }

    public List<AgentPool> getAllAgentPools() {
        return agentPoolJpaRepo.findAll();
    }

    public AgentPool addAgentPool(AgentPool agentpool) {
        return agentPoolJpaRepo.save(agentpool);
    }

    public void delAgentPoolByUuid(String uuid) {
        agentPoolJpaRepo.delete(uuid);
    }

    public AgentPool getAgentPoolByName(String agentPoolName) {
        return agentPoolJpaRepo.getByName(agentPoolName);
    }

    public AgentPool getAgentPoolByUuid(String agentPoolUuid) {
        return agentPoolJpaRepo.findOne(agentPoolUuid);
    }

    /*public List<Agent> getAllAgentsByPoolUuid(String uuid) {
        return agentPoolJpaRepo.getAllAgentsByPoolUuid(uuid);
    }*/

    /*public List<Agent> getOnLineAgentsByPoolUuid(String poolUuid) {
        return agentPoolJpaRepo.getOnLineAgentsByPoolUuid(poolUuid);
    }*/
}
