package com.yq.demo.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.yq.demo.entity.AgentProperty;

public class AgentPropertyDAO {
    /** Logger */
    private static final Logger log = Logger.getLogger(AgentPropertyDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public List<AgentProperty> getAllAgentProperties() {
        String hsql = "from agent_property";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);

        return query.list();
    }

    public List<AgentProperty> getAgentPropertiesByAgentUuid(String agentUuid) {
        String hsql = "from agent_property where agentid = ? and name like ?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, agentUuid);
        //name like '%'
        query.setString(1, "%");
        log.info("hsql:" + hsql + ", agentUuid:" + agentUuid);

        return query.list();
    }

    public AgentProperty getAgentPropertiesByAgentUuidAndName(String agentUuid, String name) {
        String hql = "from agent_property where agentid=? and name=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, agentUuid);
        query.setString(1, name);

        return (AgentProperty)query.uniqueResult();
    }

    public void addAgentProperty(AgentProperty agentProperty) {
        sessionFactory.getCurrentSession().save(agentProperty);
    }

    public int delAgentPropertiesByAgentUuid(String agentUuid) {
        String hql = "delete agent_property where agentid = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, agentUuid);

        return query.executeUpdate();
    }

}
