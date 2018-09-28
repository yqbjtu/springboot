package com.yq.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yq.demo.entity.AgentProperty;

@Repository
public interface AgentPropertyJpaRepository extends JpaRepository<AgentProperty, String> {

    public List<AgentProperty> getByAgentUuid(String agentUuid);
    public void deleteByAgentUuid(String agentUuid);
    //this will generate a sql like this 'select a from AgentProperty a where a.agentUuid = ?1 and a.name = ?2' automatically.
    //https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.entity-persistence.saving-entites
    public AgentProperty getAgentPropertyByAgentUuidAndName(String agentUuid, String name);

    /*
     *
     */
}
