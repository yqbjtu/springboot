package com.yq.demo.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yq.demo.common.AgentStatusEnum;
import com.yq.demo.entity.Agent;

@Repository
public interface AgentJpaRepository extends JpaRepository<Agent, String> {

    public Agent getByName(String name);

    @Query(value = "update #{#entityName} a set a.status=?1", nativeQuery = true)
    public void updateAllAgentsStatus(AgentStatusEnum status);

}