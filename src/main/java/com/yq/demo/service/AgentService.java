package com.yq.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yq.demo.common.AgentStatusEnum;
import com.yq.demo.dao.AgentJpaRepository;
import com.yq.demo.entity.Agent;

@Service
@Transactional
public class AgentService {
    @Autowired
    private AgentJpaRepository agentJpaRepo;

    public long agentCount(){
        return agentJpaRepo.count();
    }

    public List<Agent> getAllAgents() {
        return agentJpaRepo.findAll();
    }

    public Agent addAgent(Agent agent) {
        return agentJpaRepo.save(agent);
    }

    public void delAgentByUuid(String uuid) {
         agentJpaRepo.delete(uuid);
    }

    public void updateAllAgentsStatus(AgentStatusEnum status) {
         agentJpaRepo.updateAllAgentsStatus(status);
    }

    public Agent updateAgent(Agent agent) {
        return agentJpaRepo.save(agent);
    }

    public Agent getAgentByUuid(String uuid) {
        return agentJpaRepo.findOne(uuid);
    }

    public Agent getAgentByName(String name) {
        return agentJpaRepo.getByName(name);
    }
}
