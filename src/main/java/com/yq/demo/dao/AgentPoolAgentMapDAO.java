package com.yq.demo.dao;


import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.yq.demo.entity.Agent;
import com.yq.demo.entity.AgentPool;
import com.yq.demo.entity.AgentPoolAgentMap;

public class AgentPoolAgentMapDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void addAgent2AgentPool(AgentPoolAgentMap agentPoolAgentMap) {
        sessionFactory.getCurrentSession().save(agentPoolAgentMap);
    }

    public boolean delAgentsMapByPoolUuid(String poolUuid) {
        return delAgentsMapByPoolUuid(sessionFactory, poolUuid);
    }

    public static boolean delAgentsMapByPoolUuid(SessionFactory sessionFactory, String poolUuid) {
        String hql = "delete agentpoolagentmap where agentpoolid = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, poolUuid);

        return (query.executeUpdate() >= 0);
    }

    public boolean delAgentsMapByAgentUuid(String agentUuid) {
        return delAgentsMapByAgentUuid(sessionFactory, agentUuid);
    }

    public static boolean delAgentsMapByAgentUuid(SessionFactory sessionFactory, String agentUuid) {
        String hql = "delete agentpoolagentmap where agentid = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, agentUuid);

        return (query.executeUpdate() >= 0);
    }
}
