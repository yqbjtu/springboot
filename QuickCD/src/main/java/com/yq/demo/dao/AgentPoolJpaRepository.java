package com.yq.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yq.demo.entity.AgentPool;

@Repository
public interface AgentPoolJpaRepository extends JpaRepository<AgentPool, String> {

    public AgentPool getByName(String name);

}