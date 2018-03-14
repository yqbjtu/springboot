package com.yq.demo.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.yq.demo.entity.EnvEntry;


public class EnvEntryDAO {
    /** Logger */
    private static final Logger log = Logger.getLogger(EnvEntryDAO.class.getName());

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public List<EnvEntry> getAllEnvEntries() {

        String hsql = "from enventry";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);

        return query.list();
    }

    public List<EnvEntry> getEnvEntriesByEnvUuid(String envUuid)  {
        String hsql = "from enventry where envid = ? order by seq";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, envUuid);

        return query.list();
    }

    public EnvEntry getEnvEntryByUuid(String envEntryUuid) {

        String hsql = "from enventry where id = ?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, envEntryUuid);

        return (EnvEntry)query.uniqueResult();
    }

    public boolean delEnvEntryByUuid(String envEntryUuid) {
        boolean result = true;
        String hql = "delete enventry where id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, envEntryUuid);
        int count = query.executeUpdate();
        log.info("affected rows:" + count);

        return count == 1 ? true : false;
    }

    public boolean delEnvEntryByEnvUuid(String envUuid) {
        boolean result = true;
        String hql = "delete enventry where envid = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, envUuid);
        int count = query.executeUpdate();
        log.info("del affected rows:" + count);

        return result;
    }
    public void addEnvEntry(EnvEntry envEntry) {
        if (envEntry.getName() == null || envEntry.getEnvUuid() == null) {
            log.severe("envEntry name or envUuid information is not complete.");
        }

        Integer seq = getEnvEntriesByEnvUuid(envEntry.getEnvUuid()).size() + 1;
        envEntry.setSeq(seq);
        sessionFactory.getCurrentSession().save(envEntry);
    }

    public boolean updateEnvEntry(EnvEntry envEntry) {
        String hql = "update enventry set name=?, value=?, type=?, secure=?  where id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, envEntry.getName());
        query.setString(1, envEntry.getValue());
        query.setInteger(2, envEntry.getType());
        query.setInteger(3, envEntry.getSecure()? 1 : 0);
        return (query.executeUpdate() > 0);
    }
}
