package com.yq.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yq.demo.dao.AgentPropertyJpaRepository;
import com.yq.demo.entity.AgentProperty;

@Service
@Transactional
public class AgentPropertyService {
    @Autowired
    private AgentPropertyJpaRepository AgentPropJpaRepo;

    public List<AgentProperty> getAllAgentProperties() {
        return AgentPropJpaRepo.findAll();
    }

    public void addAgentProperty(AgentProperty agentProperty) {
        AgentPropJpaRepo.save(agentProperty);
    }

    public void delAgentPropertiesByAgentUuid(String agentUuid) {
        AgentPropJpaRepo.deleteByAgentUuid(agentUuid);
    }

    public AgentProperty getAgentPropertyByAgentUuidAndName(String agentUuid, String name) {
        return AgentPropJpaRepo.getAgentPropertyByAgentUuidAndName(agentUuid, name);
    }

    public List<AgentProperty> getAgentPropertiesByAgentUuid(String agentUuid) {
        return AgentPropJpaRepo.getByAgentUuid(agentUuid);
    }
}
