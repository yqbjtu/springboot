package com.yq.demo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;

import com.yq.demo.common.AgentStatusEnum;
import com.yq.demo.entity.Agent;

public class AgentDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public List<Agent> getAllAgents() {
        String hsql = "from agent";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);

        return query.list();
    }

    public Agent getAgentByName(String agentName) {
        String hsql="from agent where name=?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, agentName);

        return (Agent)query.uniqueResult();
    }
    /**
     *
     * @param agentname
     * @return
     */
    public List<Agent> getAgentByStatus(AgentStatusEnum status) {
        String hsql="from agent where status=?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setInteger(0, status.getIndex());

        return query.list();
    }

    public Agent getAgentByUuid(String uuid) {
        String hql = "from agent where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, uuid);

        return (Agent)query.uniqueResult();
    }

    public void addAgent(Agent agent) {
        sessionFactory.getCurrentSession().save(agent);
    }

    public boolean delAgentByUuid(String uuid) {
        //必须先删除agent和agentpool关联
        boolean mapDelOK = AgentPoolAgentMapDAO.delAgentsMapByAgentUuid(sessionFactory, uuid);

        //删除所有的agentProperties
        String hql = "delete agent where id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, uuid);

        return (query.executeUpdate() > 0);
    }

    public int updateAllAgentsStatus(AgentStatusEnum status) {
        String hql = "update agent set status=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setInteger(0, status.getIndex());

        return query.executeUpdate();
    }

    public boolean updateAgent(Agent agent) {
        String hql =
            "update agent set name=?, host=?, port=?, status=?, maxbuild=?, description=?, data=? where id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        query.setString(0, agent.getName());
        query.setString(1, agent.getHost());
        query.setInteger(2, agent.getPort());
        query.setInteger(3, agent.getStatus().getIndex());
        query.setInteger(4, agent.getMaxbuild());
        query.setString(5, agent.getDesc());
        query.setString(6, agent.getData());
        query.setString(7, agent.getUuid());

        return (query.executeUpdate() > 0);
    }

    public List<Agent> filter(Agent agent) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Agent.class);
        criteria.add(Expression.like("name", agent.getName()));
        return criteria.list();
    }
}
