package com.yq.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.yq.demo.entity.Agent;
import com.yq.demo.entity.AgentPool;
import com.yq.demo.entity.AgentPoolAgentMap;


public class AgentPoolDAO {
    /** Logger */
    private static final Logger log = Logger.getLogger(AgentPoolDAO.class.getName());

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public List<AgentPool> getAllAgentPools() {
        String hsql = "from agentpool";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);

        return query.list();
    }

    public List<Agent> getAllAgentsByPoolUuid(String uuid) {
        String hsql = "from agentpoolagentmap where agentpoolid = ?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, uuid);

        List<AgentPoolAgentMap> maplist = query.list();
        if (null != maplist) {
            log.info("agentpool has agents, size is " + maplist.size() + " AgentPoolUuid:" + uuid);
        }
        List<Agent> agentList = new ArrayList<Agent>();
        hsql = "from agent where id= ?";
        for (AgentPoolAgentMap map: maplist) {
            String agentUuid = map.getAgentUuid();
            query = session.createQuery(hsql);
            query.setString(0, agentUuid);

            Agent agent = (Agent)query.uniqueResult();
            if (null != agent) {
                agentList.add(agent);
            }
        }

        return agentList;
    }

    public List<Agent> getOnLineAgentsByPoolUuid(String poolUuid) {
        String hsql = "from agentpoolagentmap where agentpoolid = ?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, poolUuid);

        List<AgentPoolAgentMap> maplist = query.list();
        if (null != maplist) {
            log.info("agentpool has " + maplist.size() + " agent(s). AgentPoolUuid:" + poolUuid);
        }

        List<Agent> agentList = new ArrayList<Agent>();
        hsql = "from agent where id= ? and status=1";
        for (AgentPoolAgentMap map: maplist) {
            String agentUuid = map.getAgentUuid();
            query = session.createQuery(hsql);
            query.setString(0, agentUuid);

            Agent agent = (Agent)query.uniqueResult();
            if (null != agent) {
                agentList.add(agent);
            }
        }
        log.info("agentpool has " + maplist.size() + " online agent(s). AgentPoolUuid:" + poolUuid);

        return agentList;
    }

    public boolean addAgent2AgentPool(String agentUuid, String poolUuid) {
        String hsql="from agentpool where name=?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, agentUuid);
        query.setString(1, poolUuid);

        return (query.executeUpdate() > 0);
    }

    public AgentPool getAgentPoolByName(String agentPoolName){
        String hsql="from agentpool where name=?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, agentPoolName);

        return (AgentPool)query.uniqueResult();
    }

    public AgentPool getAgentPoolByUuid(String uuid) {
        String hql = "from agentpool where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, uuid);

        return (AgentPool)query.uniqueResult();
    }

    public void addAgentPool(AgentPool agentpool) {
        sessionFactory.getCurrentSession().save(agentpool);
    }

    public boolean delAgentPoolByUuid(String uuid) {
        //agentpool如果被project或者step使用了那就需要先修改project和step
        //先删除agentpool和agent关联关系
        boolean delOk = AgentPoolAgentMapDAO.delAgentsMapByPoolUuid(sessionFactory, uuid);

        String hql = "delete agentpool where id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, uuid);

        return (query.executeUpdate() > 0);
    }
}
