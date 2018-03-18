package com.yq.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yq.demo.entity.AgentProperty;

@Repository
public interface AgentPropertyJpaRepository extends JpaRepository<AgentProperty, String> {

    public List<AgentProperty> getByAgentUuid(String agentUuid);
    public void deleteByAgentUuid(String agentUuid);
    public AgentProperty getAgentPropertyByAgentUuidAndName(String agentUuid, String name);
}
