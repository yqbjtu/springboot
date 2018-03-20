package com.yq.demo.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yq.demo.common.AgentStatusEnum;
import com.yq.demo.entity.Agent;

@Repository
public interface AgentJpaRepository extends JpaRepository<Agent, String> {

    public Agent getByName(String name);

    //When adding the Query, it is the same with getByName method.
    @Query("from agent a where a.name = ?1")
    public Agent findByArgOne(String name);

    @Query(value = "SELECT * FROM agent WHERE name = ?1", nativeQuery = true)
    Agent findByName(String name);

    //… where x.name like ?1
    public List<Agent> findByNameLike(String pattern);

    //… where x.name=?1 order by x.name desc. 如果只是findOrderByNameDesc(String name)， 程序无法启动
    public List<Agent> findByNameOrderByNameDesc(String name);
    @Query(value = "update #{#entityName} a set a.status=?1", nativeQuery = true)
    public int updateAllAgentsStatus(AgentStatusEnum status);

    //… where x.port between ?1 and ?2
    public List<Agent> findByPortBetween(Integer min, Integer max);

}