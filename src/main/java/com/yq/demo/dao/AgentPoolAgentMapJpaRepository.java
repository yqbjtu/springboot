package com.yq.demo.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yq.demo.entity.AgentPoolAgentMap;

@Repository
public interface AgentPoolAgentMapJpaRepository extends JpaRepository<AgentPoolAgentMap, String> {

    public void deleteByAgentPoolUuid(String poolUuid);
    public List<AgentPoolAgentMap> getByAgentPoolUuid(String poolUuid);

}